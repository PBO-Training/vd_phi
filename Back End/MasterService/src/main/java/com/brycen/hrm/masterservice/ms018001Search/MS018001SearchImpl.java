package com.brycen.hrm.masterservice.ms018001Search;

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

/**
 * [Description]:<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS018001SearchImpl implements MS018001SearchIService {

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
    public BaseResponse search(MS018001SearchRequest searchRequest, int companyID) {
        int pageSize = searchRequest.getPageSize();
        StringBuffer countString = new StringBuffer("SELECT COUNT(role_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM role r ");
        StringBuffer whereString = new StringBuffer("WHERE r.company_id= :companyID AND r.is_delete=0 ");
        StringBuffer orderByString = new StringBuffer("order by role_name asc ");
        StringBuffer queryString = new StringBuffer();
        if (!CheckValueService.checkNull(searchRequest.getRoleCode())) {
            whereString.append("AND r.role_code= :roleCode ");
        }
        if (!CheckValueService.checkNull(searchRequest.getRoleName())) {
            whereString.append("AND r.role_name LIKE :roleName ");
        }
        queryString.append(selectString).append(fromString).append(whereString).append(orderByString);

        countString.append(fromString).append(whereString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
        if (!CheckValueService.checkNull(searchRequest.getRoleCode())) {
            query.setParameter("roleCode", searchRequest.getRoleCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getRoleName())) {
            query.setParameter("roleName", "%" + searchRequest.getRoleName() + "%");
        }

        int totalElements = ((Number) query.getSingleResult()).intValue();

        query = em.createNativeQuery(queryString.toString(), RoleEntity.class);
        query.setParameter("companyID", companyID);

        if (!CheckValueService.checkNull(searchRequest.getRoleCode())) {
            query.setParameter("roleCode", searchRequest.getRoleCode());
        }

        if (!CheckValueService.checkNull(searchRequest.getRoleName())) {
            query.setParameter("roleName", "%" + searchRequest.getRoleName() + "%");
        }

        if (searchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        @SuppressWarnings("unchecked")
        List<RoleEntity> roleList = query.setFirstResult(searchRequest.getPageNum() * pageSize).setMaxResults(pageSize).getResultList();
        List<Object> result = roleList.stream().map(MS018001SearchResponse::new).collect(Collectors.toList());
        ContentResponse content = new ContentResponse(result, searchRequest.getPageNum(), pageSize, totalElements);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(content);
        baseRes.setError(null);
        return baseRes;
    }

}
