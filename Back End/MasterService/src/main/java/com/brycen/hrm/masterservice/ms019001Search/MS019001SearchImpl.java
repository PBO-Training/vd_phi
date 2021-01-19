package com.brycen.hrm.masterservice.ms019001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.entity.ScopeWorkEntity;
/**
 * [Description]:<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS019001SearchImpl implements MS019001SearchIService {

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
    public BaseResponse search(MS019001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        StringBuffer countString = new StringBuffer("SELECT COUNT(scope_work_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM scope_work s ");
        StringBuffer whereString = new StringBuffer("WHERE s.company_id= :companyID AND s.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by scope_work_name asc ");
        StringBuffer queryString = new StringBuffer();
        if (!CheckValueService.checkNull(searchRequest.getScopeWorkCode())) {
            whereString.append("AND s.scope_work_code= :scopeWorkCode ");
        }
        if (!CheckValueService.checkNull(searchRequest.getScopeWorkName())) {
            whereString.append("AND s.scope_work_name LIKE :scopeWorkName ");
        }
        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);

        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getScopeWorkCode())) {
            query.setParameter("scopeWorkCode", searchRequest.getScopeWorkCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getScopeWorkName())) {
            query.setParameter("scopeWorkName", "%" + searchRequest.getScopeWorkName() + "%");
        }

        int totalElements = ((Number) query.getSingleResult()).intValue();

        query = em.createNativeQuery(queryString.toString(), ScopeWorkEntity.class);
        query.setParameter("companyID", companyID);

        if (!CheckValueService.checkNull(searchRequest.getScopeWorkCode())) {
            query.setParameter("scopeWorkCode", searchRequest.getScopeWorkCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getScopeWorkName())) {
            query.setParameter("scopeWorkName", "%" + searchRequest.getScopeWorkName() + "%");
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        @SuppressWarnings("unchecked")
        List<ScopeWorkEntity> scopeWorkList = query.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(pageSize).getResultList();
        List<Object> result = scopeWorkList.stream().map(MS019001SearchResponse::new).collect(Collectors.toList());
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        baseRes.setError(null);
        return baseRes;

        
    }

}
