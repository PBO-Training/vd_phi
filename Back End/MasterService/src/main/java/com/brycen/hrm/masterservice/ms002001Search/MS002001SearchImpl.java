package com.brycen.hrm.masterservice.ms002001Search;

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
import com.brycen.hrm.entity.DepartmentEntity;

/**
 * [Description]: Class connect to database to search department and send result to interface<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS002001SearchImpl implements MS002001SearchService {
    @Autowired
    public EntityManager em;

    private Query query;

    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse search(MS002001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        List<DepartmentEntity> departmentList;
        StringBuffer countString = new StringBuffer("SELECT COUNT(department_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM department d ");
        StringBuffer whereString = new StringBuffer("WHERE d.company_id= :companyID AND d.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by department_name asc ");
        StringBuffer queryString = new StringBuffer();

        // Build SQL query
        if (!CheckValueService.checkNull(searchRequest.getDepartmentCode())) {
            whereString.append("AND d.department_code= :departmentCode ");
        }

        if (!CheckValueService.checkNull(searchRequest.getDepartmentName())) {
            whereString.append("AND d.department_name LIKE :departmentName ");
        }

        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);

        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getDepartmentCode())) {
            query.setParameter("departmentCode", searchRequest.getDepartmentCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getDepartmentName())) {
            query.setParameter("departmentName", "%" + searchRequest.getDepartmentName() + "%");
        }
        int totalElements = ((Number) query.getSingleResult()).intValue();

        // Create query sql to get data
        query = em.createNativeQuery(queryString.toString(), DepartmentEntity.class);

        // Set parameter to get data
        query.setParameter("companyID", companyID);

        if (!CheckValueService.checkNull(searchRequest.getDepartmentCode())) {
            query.setParameter("departmentCode", searchRequest.getDepartmentCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getDepartmentName())) {
            query.setParameter("departmentName", "%" + searchRequest.getDepartmentName() + "%");
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        departmentList = query.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(pageSize).getResultList();
        List<Object> result = departmentList.stream().map(MS002001SearchResponse::new).collect(Collectors.toList());
        if (searchRequest.getType() != null && searchRequest.getType().equals(TypeInitValue.CUSTOM_TYPE)) {
            result.add(new MS002001SearchResponse(0L, TypeInitValue.OTHER));
            result.add(0, new MS002001SearchResponse(-1L, TypeInitValue.ALL));
        }
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        return baseRes;
    }
}
