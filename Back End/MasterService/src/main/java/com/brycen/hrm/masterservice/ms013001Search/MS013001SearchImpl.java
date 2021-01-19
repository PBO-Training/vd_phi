package com.brycen.hrm.masterservice.ms013001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.entity.HolidayEntity;

/**
 * [Description]: Class connect to database to search holiday and send result to interface<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS013001SearchImpl implements MS013001SearchService {
    @Autowired
    public EntityManager em;

    private Query query;

    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse search(MS013001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        List<HolidayEntity> holidayList;
        StringBuffer countString = new StringBuffer("SELECT COUNT(holiday_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM holiday hol ");
        StringBuffer whereString = new StringBuffer("WHERE hol.company_id= :companyID AND hol.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by hol.holiday_year asc ");
        StringBuffer queryString = new StringBuffer();

        // Build SQL query
        if (!CheckValueService.checkNull(searchRequest.getHolidayName())) {
            whereString.append("AND hol.holiday_name LIKE :holidayName ");
        }
        if (searchRequest.getHolidayYear() > 0) {
            whereString.append("AND hol.holiday_year = :holidayYear ");
        }

        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);

        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getHolidayName())) {
            query.setParameter("holidayName", "%" + searchRequest.getHolidayName() + "%");
        }

        if (searchRequest.getHolidayYear() != 0) {
            query.setParameter("holidayYear", searchRequest.getHolidayYear());
        }
        int totalElements = ((Number) query.getSingleResult()).intValue();

        // Create query sql to get data
        query = em.createNativeQuery(queryString.toString(), HolidayEntity.class);

        // Set parameter to get data
        query.setParameter("companyID", companyID);

        if (!CheckValueService.checkNull(searchRequest.getHolidayName())) {
            query.setParameter("holidayName", "%" + searchRequest.getHolidayName() + "%");
        }

        if (searchRequest.getHolidayYear() != 0) {
            query.setParameter("holidayYear", searchRequest.getHolidayYear());
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        holidayList = query.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(pageSize).getResultList();
        List<Object> result = holidayList.stream().map(MS013001SearchResponse::new).collect(Collectors.toList());
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        return baseRes;
    }

}
