package com.brycen.hrm.masterservice.ms006001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.entity.LevelLanguageEntity;

/**
 * [Description]: Class connect to database to search levelLanguage and send result to interface<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS006001SearchImpl implements MS006001SearchService {
    @Autowired
    public EntityManager em;

    private Query query;

    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse search(MS006001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        List<LevelLanguageEntity> levelLanguageList;
        StringBuffer countString = new StringBuffer("SELECT COUNT(level_language_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM level_language ll ");
        StringBuffer whereString = new StringBuffer("WHERE ll.company_id= :companyID AND ll.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by level_language_name asc ");
        StringBuffer queryString = new StringBuffer();

        // Build SQL query
        if (!CheckValueService.checkNull(searchRequest.getLevelLanguageCode())) {
            whereString.append("AND ll.level_language_code= :levelLanguageCode ");
        }

        if (!CheckValueService.checkNull(searchRequest.getLevelLanguageName())) {
            whereString.append("AND ll.level_language_name LIKE :levelLanguageName ");
        }

        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);

        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getLevelLanguageCode())) {
            query.setParameter("levelLanguageCode", searchRequest.getLevelLanguageCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getLevelLanguageName())) {
            query.setParameter("levelLanguageName", "%" + searchRequest.getLevelLanguageName() + "%");
        }

        int totalElements = ((Number) query.getSingleResult()).intValue();

        // Create query sql to get data
        query = em.createNativeQuery(queryString.toString(), LevelLanguageEntity.class);
        query.setParameter("companyID", companyID);

        if (!CheckValueService.checkNull(searchRequest.getLevelLanguageCode())) {
            query.setParameter("levelLanguageCode", searchRequest.getLevelLanguageCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getLevelLanguageName())) {
            query.setParameter("levelLanguageName", "%" + searchRequest.getLevelLanguageName() + "%");
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        levelLanguageList = query.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(pageSize).getResultList();
        List<Object> result = levelLanguageList.stream().map(MS006001SearchResponse::new).collect(Collectors.toList());
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        return baseRes;
    }
}
