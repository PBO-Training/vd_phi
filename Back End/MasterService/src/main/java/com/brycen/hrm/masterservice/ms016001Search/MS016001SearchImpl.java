package com.brycen.hrm.masterservice.ms016001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.entity.EvaluateProjectEntity;

/**
 * [Description]: Class connect to database to search evaluateProject and send result to interface<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS016001SearchImpl implements MS016001SearchService {
    /**
     * Use to create query and excute it
     */
    @Autowired
    public EntityManager em;

    /**
     * Use to control query execution
     */
    private Query query;

    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse search(MS016001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        List<EvaluateProjectEntity> evaluateProjectList;
        StringBuffer countString = new StringBuffer("SELECT COUNT(evaluate_project_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM evaluate_project lp ");
        StringBuffer whereString = new StringBuffer("WHERE lp.company_id= :companyID AND lp.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by evaluate_project_name asc ");
        StringBuffer queryString = new StringBuffer();

        // Take data with evaluateProjectCode
        if (!CheckValueService.checkNull(searchRequest.getEvaluateProjectCode())) {
            whereString.append("AND lp.evaluate_project_code= :evaluateProjectCode ");
        }

        // Take data with evaluateProjectName
        if (!CheckValueService.checkNull(searchRequest.getEvaluateProjectName())) {
            whereString.append("AND lp.evaluate_project_name LIKE :evaluateProjectName ");
        }

        // Complete syntax SQL search evaluateProject
        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);

        // Complete syntax SQL count evaluateProject
        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set value to parameter evaluateProjectCode
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getEvaluateProjectCode())) {
            query.setParameter("evaluateProjectCode", searchRequest.getEvaluateProjectCode());
        }

        // Set value to parameter evaluateProjectName
        if (!CheckValueService.checkNull(searchRequest.getEvaluateProjectName())) {
            query.setParameter("evaluateProjectName", "%" + searchRequest.getEvaluateProjectName() + "%");
        }

        int totalElements = ((Number) query.getSingleResult()).intValue();

        query = em.createNativeQuery(queryString.toString(), EvaluateProjectEntity.class);
        query.setParameter("companyID", companyID);

        // Set value to parameter evaluateProjectCode
        if (!CheckValueService.checkNull(searchRequest.getEvaluateProjectCode())) {
            query.setParameter("evaluateProjectCode", searchRequest.getEvaluateProjectCode());
        }

        // Set value to parameter evaluateProjectCode
        if (!CheckValueService.checkNull(searchRequest.getEvaluateProjectName())) {
            query.setParameter("evaluateProjectName", "%" + searchRequest.getEvaluateProjectName() + "%");
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        evaluateProjectList = query.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(pageSize).getResultList();
        List<Object> result = evaluateProjectList.stream().map(MS016001SearchResponse::new).collect(Collectors.toList());
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        return baseRes;
    }
}
