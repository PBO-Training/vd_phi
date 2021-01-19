package com.brycen.hrm.masterservice.ms012001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.entity.VacationTypeEntity;

/**
 * [Description]: Class connect to database to search vacation type and send result to interface<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS012001SearchImpl implements MS012001SearchService {
    @Autowired
    public EntityManager em;

    private Query query;

    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse search(MS012001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        List<VacationTypeEntity> vacationTypeList;
        StringBuffer countString = new StringBuffer("SELECT COUNT(vacation_type_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM vacation_type v ");
        StringBuffer whereString = new StringBuffer("WHERE v.company_id= :companyID AND v.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by vacation_type_name asc ");
        StringBuffer queryString = new StringBuffer();

        // Build SQL query
        if (!CheckValueService.checkNull(searchRequest.getVacationTypeCode())) {
            whereString.append("AND v.vacation_type_code= :vacationTypeCode ");
        }

        if (!CheckValueService.checkNull(searchRequest.getVacationTypeName())) {
            whereString.append("AND v.vacation_type_name LIKE :vacationTypeName ");
        }

        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);

        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getVacationTypeCode())) {
            query.setParameter("vacationTypeCode", searchRequest.getVacationTypeCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getVacationTypeName())) {
            query.setParameter("vacationTypeName", "%" + searchRequest.getVacationTypeName() + "%");
        }
        int totalElements = ((Number) query.getSingleResult()).intValue();

        // Create query sql to get data
        query = em.createNativeQuery(queryString.toString(), VacationTypeEntity.class);

        // Set parameter to get data
        query.setParameter("companyID", companyID);

        if (!CheckValueService.checkNull(searchRequest.getVacationTypeCode())) {
            query.setParameter("vacationTypeCode", searchRequest.getVacationTypeCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getVacationTypeName())) {
            query.setParameter("vacationTypeName", "%" + searchRequest.getVacationTypeName() + "%");
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        vacationTypeList = query.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(pageSize).getResultList();
        List<Object> result = vacationTypeList.stream().map(MS012001SearchResponse::new).collect(Collectors.toList());
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        return baseRes;
    }
}
