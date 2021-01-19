package com.brycen.hrm.masterservice.ms008001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.entity.StatusEmployeeEntity;

/**
 * [Description]: Search Service Implementation for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS008001SearchImpl implements MS008001SearchIService {

    /**
     * Call entity manager to support creation a native query statement
     */
    @Autowired
    public EntityManager em;

    /**
     * Call query to save an instance native query statement
     */
    private Query query;

    @Override
    public BaseResponse search(MS008001SearchRequest ms008001SearchRequest, int companyID) {
        int pageSize = ms008001SearchRequest.getPageSize();
        StringBuffer countString = new StringBuffer("SELECT COUNT(status_employee_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM status_employee se ");
        StringBuffer whereString = new StringBuffer("WHERE se.company_id= :companyID AND se.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by status_employee_name asc ");
        StringBuffer queryString = new StringBuffer();
        if (!CheckValueService.checkNull(ms008001SearchRequest.getStatusEmployeeCode())) {
            whereString.append("AND se.status_employee_code= :statusEmployeeCode ");
        }
        if (!CheckValueService.checkNull(ms008001SearchRequest.getStatusEmployeeName())) {
            whereString.append("AND se.status_employee_name LIKE :statusEmployeeName ");
        }
        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);

        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(ms008001SearchRequest.getStatusEmployeeCode())) {
            query.setParameter("statusEmployeeCode", ms008001SearchRequest.getStatusEmployeeCode());
        }

        if (!CheckValueService.checkNull(ms008001SearchRequest.getStatusEmployeeName())) {
            query.setParameter("statusEmployeeName", "%" + ms008001SearchRequest.getStatusEmployeeName() + "%");
        }

        int totalElements = ((Number) query.getSingleResult()).intValue();

        query = em.createNativeQuery(queryString.toString(), StatusEmployeeEntity.class);
        query.setParameter("companyID", companyID);

        if (!CheckValueService.checkNull(ms008001SearchRequest.getStatusEmployeeCode())) {
            query.setParameter("statusEmployeeCode", ms008001SearchRequest.getStatusEmployeeCode());
        }

        if (!CheckValueService.checkNull(ms008001SearchRequest.getStatusEmployeeName())) {
            query.setParameter("statusEmployeeName", "%" + ms008001SearchRequest.getStatusEmployeeName() + "%");
        }

        if (ms008001SearchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        @SuppressWarnings("unchecked")
        List<StatusEmployeeEntity> statusEmployeeList = query.setFirstResult(ms008001SearchRequest.getPageNum() * pageSize).setMaxResults(pageSize)
                .getResultList();
        List<Object> result = statusEmployeeList.stream().map(MS008001SearchResponse::new).collect(Collectors.toList());
        ContentResponse content = new ContentResponse(result, ms008001SearchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        baseRes.setError(null);
        return baseRes;
    }

}
