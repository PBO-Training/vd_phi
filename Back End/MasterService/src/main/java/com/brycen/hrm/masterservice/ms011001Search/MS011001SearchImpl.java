package com.brycen.hrm.masterservice.ms011001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.entity.LevelProjectEntity;

/**
 * [Description]: Class connect to database to search levelProject and send result to interface<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS011001SearchImpl implements MS011001SearchService {
    @Autowired
    public EntityManager em;

    private Query query;

    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse search(MS011001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        List<LevelProjectEntity> levelProjectList;
        StringBuffer countString = new StringBuffer("SELECT COUNT(level_project_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM level_project lp ");
        StringBuffer whereString = new StringBuffer("WHERE lp.company_id= :companyID AND lp.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by level_project_name asc ");
        StringBuffer queryString = new StringBuffer();

        // Take data with levelProjectCode
        if (!CheckValueService.checkNull(searchRequest.getLevelProjectCode())) {
            whereString.append("AND lp.level_project_code= :levelProjectCode ");
        }

        // Take data with levelProjectName
        if (!CheckValueService.checkNull(searchRequest.getLevelProjectName())) {
            whereString.append("AND lp.level_project_name LIKE :levelProjectName ");
        }

        // Complete syntax SQL search levelProject
        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);

        // Complete syntax SQL count levelProject
        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set value to parameter levelProjectCode
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getLevelProjectCode())) {
            query.setParameter("levelProjectCode", searchRequest.getLevelProjectCode());
        }

        // Set value to parameter levelProjectName
        if (!CheckValueService.checkNull(searchRequest.getLevelProjectName())) {
            query.setParameter("levelProjectName", "%" + searchRequest.getLevelProjectName() + "%");
        }

        int totalElements = ((Number) query.getSingleResult()).intValue();

        query = em.createNativeQuery(queryString.toString(), LevelProjectEntity.class);
        query.setParameter("companyID", companyID);

        // Set value to parameter levelProjectCode
        if (!CheckValueService.checkNull(searchRequest.getLevelProjectCode())) {
            query.setParameter("levelProjectCode", searchRequest.getLevelProjectCode());
        }

        // Set value to parameter levelProjectCode
        if (!CheckValueService.checkNull(searchRequest.getLevelProjectName())) {
            query.setParameter("levelProjectName", "%" + searchRequest.getLevelProjectName() + "%");
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        levelProjectList = query.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(pageSize).getResultList();
        List<Object> result = levelProjectList.stream().map(MS011001SearchResponse::new).collect(Collectors.toList());
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        return baseRes;
    }
}
