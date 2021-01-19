package com.brycen.hrm.masterservice.ms001001Search;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.entity.UserEntity;

/**
 * [Description]: Search Service Implementation for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS001001SearchImpl implements MS001001SearchIService {

    /**
     * Call entity manager to support creation a native query statement
     */
    @Autowired
    public EntityManager em;

    /**
     * Call query to save an instance native query statement
     */
    private Query query;

    @SuppressWarnings("unchecked")
    @Override
    public ContentResponse searchUser(MS001001SearchRequest ms001001SearchRequest, int companyID) {

        int pageSize = ms001001SearchRequest.getPageSize();
        List<UserEntity> userList;
        StringBuffer countString = new StringBuffer("SELECT COUNT(user_id) ");
        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM user u ");
        StringBuffer whereString = new StringBuffer("WHERE u.company_id = :companyID ");
        StringBuffer andString = new StringBuffer("AND u.is_delete = 0 ");
        StringBuffer orderByString = new StringBuffer("order by user_name asc ");
        StringBuffer queryString = new StringBuffer();

        // Build a SQL query statement
        if (!StringUtils.isEmpty(ms001001SearchRequest.getSearchName())) {
            whereString.append(" AND ").append(" u.user_name LIKE :username ");
        }
        
        // Condition of relationship
        whereString.append("AND ((IFNULL(u.role_id, 0) = :roleID) or (-1 = :roleID) or ISNULL(:roleID)) ");
        queryString.append(selectString).append(fromString).append(whereString).append(andString).append(orderByString);
        countString.append(fromString).append(whereString).append(andString);

        // Create query sql to count total data
        query = em.createNativeQuery(countString.toString());

        // Set parameter to count total data
        query.setParameter("companyID", companyID);
        if (!StringUtils.isEmpty(ms001001SearchRequest.getSearchName())) {
            query.setParameter("username", "%" + ms001001SearchRequest.getSearchName() + "%");
        }
        if (ms001001SearchRequest.getSearchRole() != null) {
            query.setParameter("roleID", ms001001SearchRequest.getSearchRole());
        }
        int totalElements = ((Number) query.getSingleResult()).intValue();

        // Create query sql to get data
        query = em.createNativeQuery(queryString.toString(), UserEntity.class);

        // Set parameters to get data
        query.setParameter("companyID", companyID);
        if (!StringUtils.isEmpty(ms001001SearchRequest.getSearchName())) {
            query.setParameter("username", "%" + ms001001SearchRequest.getSearchName() + "%");
        }
        if (ms001001SearchRequest.getSearchRole() != null) {
            query.setParameter("roleID", ms001001SearchRequest.getSearchRole());
        }

        if (ms001001SearchRequest.getPageSize() == 0) {
            pageSize = 30;
        }

        userList = query.setFirstResult(ms001001SearchRequest.getPageNum() * pageSize).setMaxResults(pageSize).getResultList();
        List<Object> result = userList.stream().map(MS001001SearchResponse::new).collect(Collectors.toList());
        ContentResponse content = new ContentResponse(result, ms001001SearchRequest.getPageNum(), pageSize, totalElements);
        return content;

    }

}
