package com.brycen.hrm.masterservice.ms023001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.entity.RoleEntity;
import com.brycen.hrm.entity.ShiftWorkOptionEntity;

/**
 * [Description]:<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS023001SearchImpl implements MS023001SearchIService {

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
    public BaseResponse search(MS023001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        StringBuffer countString = new StringBuffer("SELECT COUNT(shift_work_option_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM shift_work_option so ");
        StringBuffer whereString = new StringBuffer("WHERE so.company_id= :companyID AND so.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by shift_work_option_name asc ");
        StringBuffer queryString = new StringBuffer();
        
        if (!CheckValueService.checkNull(searchRequest.getShiftWorkOptionCode())) {
            whereString.append("AND so.shift_work_option_code= :shiftWorkOptionCode ");
        }
        if (!CheckValueService.checkNull(searchRequest.getShiftWorkOptionName())) {
            whereString.append("AND so.shift_work_option_name LIKE :shiftWorkOptionName ");
        }
        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);

        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
       
        if (!CheckValueService.checkNull(searchRequest.getShiftWorkOptionCode())) {
            query.setParameter("shiftWorkOptionCode", searchRequest.getShiftWorkOptionCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getShiftWorkOptionName())) {
            query.setParameter("shiftWorkOptionName", "%" + searchRequest.getShiftWorkOptionName() + "%");
        }

        int totalElements = ((Number) query.getSingleResult()).intValue();

        query = em.createNativeQuery(queryString.toString(), ShiftWorkOptionEntity.class);
        query.setParameter("companyID", companyID);
        
        if (!CheckValueService.checkNull(searchRequest.getShiftWorkOptionCode())) {
            query.setParameter("shiftWorkOptionCode", searchRequest.getShiftWorkOptionCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getShiftWorkOptionName())) {
            query.setParameter("shiftWorkOptionName", "%" + searchRequest.getShiftWorkOptionName() + "%");
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        @SuppressWarnings("unchecked")
        List<ShiftWorkOptionEntity> shilfworkList = query.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(pageSize).getResultList();
        List<Object> result = shilfworkList.stream().map(MS023001SearchResponse::new).collect(Collectors.toList());
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        baseRes.setError(null);
        return baseRes;
    }

}
