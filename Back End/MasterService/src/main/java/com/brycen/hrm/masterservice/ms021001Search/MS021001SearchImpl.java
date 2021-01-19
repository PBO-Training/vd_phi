package com.brycen.hrm.masterservice.ms021001Search;

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
import com.brycen.hrm.entity.LanguageCategoryEntity;

/**
 * [Description]: Class connect to database to search language category and send result to interface<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS021001SearchImpl implements MS021001SearchService {
    @Autowired
    private EntityManager em;

    private Query query;

    /**
     * [Description]: Use query to connect database and search language category with params<br/>
     * [ Remarks ]:<br/>
     *
     * @param searchRequest - A object is created to receive model from client request
     * @return content - MS021001SearchResponse - A object is created to contain data and send to client
     */
    @Override
    @SuppressWarnings("unchecked")
    public BaseResponse search(MS021001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        StringBuffer countString = new StringBuffer("SELECT COUNT(language_category_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM language_category l ");
        StringBuffer whereString = new StringBuffer("WHERE l.company_id= :companyID AND l.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by l.language_category_name, l.language_category_code asc ");
        StringBuffer queryString = new StringBuffer();
        if (!CheckValueService.checkNull(searchRequest.getLanguageCategoryCode())) {
            whereString.append(" AND l.language_category_code= :languageCategoryCode ");
        }
        if (!CheckValueService.checkNull(searchRequest.getLanguageCategoryName())) {
            whereString.append(" AND l.language_category_name LIKE :languageCategoryName ");
        }
        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);
        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getLanguageCategoryCode())) {
            query.setParameter("languageCategoryCode", searchRequest.getLanguageCategoryCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getLanguageCategoryName())) {
            query.setParameter("languageCategoryName", "%" + searchRequest.getLanguageCategoryName() + "%");
        }
        int totalElements = ((Number) query.getSingleResult()).intValue();

        query = em.createNativeQuery(queryString.toString(), LanguageCategoryEntity.class);
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getLanguageCategoryCode())) {
            query.setParameter("languageCategoryCode", searchRequest.getLanguageCategoryCode());
        }
        if (!CheckValueService.checkNull(searchRequest.getLanguageCategoryName())) {
            query.setParameter("languageCategoryName", "%" + searchRequest.getLanguageCategoryName() + "%");
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        List<LanguageCategoryEntity> languageList = query.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(searchRequest.getPageSize())
                .getResultList();
        List<Object> result = languageList.stream().map(MS021001SearchResponse::new).collect(Collectors.toList());
        if (searchRequest.getType() != null && searchRequest.getType().equals(TypeInitValue.CUSTOM_TYPE)) {
            result.add(new MS021001SearchResponse(0L, TypeInitValue.OTHER));
            result.add(0, new MS021001SearchResponse(-1L, TypeInitValue.ALL));
        }
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        return baseRes;
    }

}
