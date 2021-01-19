package com.brycen.hrm.masterservice.ms014001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.entity.StatusProjectEntity;

/**
 * [Description]: Class connect to database to search statusProject and send result to interface<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS014001SearchImpl implements MS014001SearchService {
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
    public BaseResponse search(MS014001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        List<StatusProjectEntity> statusProjectList;
        StringBuffer countString = new StringBuffer("SELECT COUNT(status_project_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM status_project sp ");
        StringBuffer whereString = new StringBuffer("WHERE sp.company_id= :companyID AND sp.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by status_project_name asc ");
        StringBuffer queryString = new StringBuffer();

        // Take data with statusProjectCode
        if (!CheckValueService.checkNull(searchRequest.getStatusProjectCode())) {
            whereString.append("AND sp.status_project_code= :statusProjectCode ");
        }

        // Take data with statusProjectCode
        if (!CheckValueService.checkNull(searchRequest.getStatusProjectName())) {
            whereString.append("AND sp.status_project_name LIKE :statusProjectName ");
        }

        // Complete syntax SQL search statusProject
        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);

        // Complete syntax SQL count statusProjec
        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set value to parameter statusProjectCode
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getStatusProjectCode())) {
            query.setParameter("statusProjectCode", searchRequest.getStatusProjectCode());
        }

        // Set value to parameter statusProjectName
        if (!CheckValueService.checkNull(searchRequest.getStatusProjectName())) {
            query.setParameter("statusProjectName", "%" + searchRequest.getStatusProjectName() + "%");
        }

        int totalElements = ((Number) query.getSingleResult()).intValue();

        // Create query sql to search total data
        query = em.createNativeQuery(queryString.toString(), StatusProjectEntity.class);
        query.setParameter("companyID", companyID);

        // Set value to parameter statusProjectCode
        if (!CheckValueService.checkNull(searchRequest.getStatusProjectCode())) {
            query.setParameter("statusProjectCode", searchRequest.getStatusProjectCode());
        }

        // Set value to parameter statusProjectName
        if (!CheckValueService.checkNull(searchRequest.getStatusProjectName())) {
            query.setParameter("statusProjectName", "%" + searchRequest.getStatusProjectName() + "%");
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        statusProjectList = query.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(pageSize).getResultList();
        List<Object> result = statusProjectList.stream().map(MS014001SearchResponse::new).collect(Collectors.toList());
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        return baseRes;
    }
}
