package com.brycen.hrm.masterservice.ms005001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.TypeInitValue;
import com.brycen.hrm.entity.LanguageEntity;

/**
 * [Description]: Class connect to database to search language and send result to interface<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS005001SearchImpl implements MS005001SearchService {
    @Autowired
    private EntityManager em;

    private Query query;

    /**
     * [Description]: Use query to connect database and search language with params<br/>
     * [ Remarks ]:<br/>
     *
     * @param searchRequest - A object is created to receive model from client request
     * @return content - MS005001SearchResponse - A object is created to contain data and send to client
     */
    @Override
    public BaseResponse search(MS005001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        StringBuffer countString = new StringBuffer("SELECT COUNT(language_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM language l ");
        StringBuffer whereString = new StringBuffer("WHERE l.company_id= :companyID AND l.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by language_name, language_code asc ");
        StringBuffer queryString = new StringBuffer();
        if (!CheckValueService.checkNull(searchRequest.getLanguageCode())) {
            whereString.append(" AND l.language_code= :languageCode ");
        }
        if (!CheckValueService.checkNull(searchRequest.getLanguageName())) {
            whereString.append(" AND l.language_name LIKE :languageName ");
        }
        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);
        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getLanguageCode())) {
            query.setParameter("languageCode", searchRequest.getLanguageCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getLanguageName())) {
            query.setParameter("languageName", "%" + searchRequest.getLanguageName() + "%");
        }
        int totalElements = ((Number) query.getSingleResult()).intValue();

        query = em.createNativeQuery(queryString.toString(), LanguageEntity.class);
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getLanguageCode())) {
            query.setParameter("languageCode", searchRequest.getLanguageCode());
        }
        if (!CheckValueService.checkNull(searchRequest.getLanguageName())) {
            query.setParameter("languageName", "%" + searchRequest.getLanguageName() + "%");
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        @SuppressWarnings("unchecked")
        List<LanguageEntity> languageList = query.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(searchRequest.getPageSize())
                .getResultList();
        List<Object> result = languageList.stream().map(MS005001SearchResponse::new).collect(Collectors.toList());
        if (searchRequest.getType() != null && searchRequest.getType().equals(TypeInitValue.CUSTOM_TYPE)) {
            result.add(new MS005001SearchResponse(0L, TypeInitValue.OTHER));
            result.add(0, new MS005001SearchResponse(-1L, TypeInitValue.ALL));
        }
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        return baseRes;
    }

}
