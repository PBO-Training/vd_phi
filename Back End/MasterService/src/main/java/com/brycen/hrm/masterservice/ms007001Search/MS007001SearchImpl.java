package com.brycen.hrm.masterservice.ms007001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.entity.PositionEmployeeEntity;

/**
 * [Description]: Class connect to database to search employee position and send result to interface<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS007001SearchImpl implements MS007001SearchService {
    @Autowired
    public EntityManager em;

    private Query query;

    @Override
    public BaseResponse search(MS007001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        StringBuffer countString = new StringBuffer("SELECT COUNT(position_employee_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM position_employee p ");
        StringBuffer whereString = new StringBuffer("WHERE p.company_id= :companyID And p.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by position_employee_name asc ");
        StringBuffer queryString = new StringBuffer();
        if (!CheckValueService.checkNull(searchRequest.getPositionEmployeeCode())) {
            whereString.append(" AND p.position_employee_code= :positionEmployeeCode ");
        }
        if (!CheckValueService.checkNull(searchRequest.getPositionEmployeeName())) {
            whereString.append("AND p.position_employee_name like :positionEmployeeName ");
        }
        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);

        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getPositionEmployeeCode())) {
            query.setParameter("positionEmployeeCode", searchRequest.getPositionEmployeeCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getPositionEmployeeName())) {
            query.setParameter("positionEmployeeName", "%" + searchRequest.getPositionEmployeeName() + "%");
        }
        int totalElements = ((Number) query.getSingleResult()).intValue();
        query = em.createNativeQuery(queryString.toString(), PositionEmployeeEntity.class);
        query.setParameter("companyID", companyID);

        if (!CheckValueService.checkNull(searchRequest.getPositionEmployeeCode())) {
            query.setParameter("positionEmployeeCode", searchRequest.getPositionEmployeeCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getPositionEmployeeName())) {
            query.setParameter("positionEmployeeName", "%" + searchRequest.getPositionEmployeeName() + "%");
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        @SuppressWarnings("unchecked")
        List<PositionEmployeeEntity> positionEmployeeList = query.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(pageSize).getResultList();
        List<Object> result = positionEmployeeList.stream().map(MS007001SearchResponse::new).collect(Collectors.toList());
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        return baseRes;
    }
}
