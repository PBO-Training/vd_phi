package com.brycen.hrm.masterservice.ms001001Delete;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.entity.UserEntity;

/**
 * [Description]:Delete Service Implementation for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS001001DeleteImpl implements MS001001DeleteIService {

    /**
     * Call entity manager to support creation a native query statement
     */
    @Autowired
    public EntityManager em;

    /**
     * Call query to save an instance native query statement
     */
    private Query query;

    @Transactional
    @Override
    public int deleteListUser(List<Long> userDelete, int companyID) {
        StringBuffer updateString = new StringBuffer("UPDATE user u ");
        StringBuffer setString = new StringBuffer("SET u.is_delete = 1 ");
        StringBuffer whereString = new StringBuffer("WHERE u.user_id IN (:userID) ");
        StringBuffer andString = new StringBuffer("AND u.company_id = :companyID");
        StringBuffer queryString = new StringBuffer();
        queryString.append(updateString).append(setString).append(whereString).append(andString);
        query = em.createNativeQuery(queryString.toString(), UserEntity.class);
        query.setParameter("userID", (Collection<Long>) userDelete);
        query.setParameter("companyID", companyID);
        int updatedQuantity = query.executeUpdate();
        return updatedQuantity;
    }

}
