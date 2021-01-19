package com.brycen.hrm.masterservice.ms008001Delete;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.entity.StatusEmployeeEntity;

/**
 * [Description]: Delete Service Implementation for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS008001DeleteImpl implements MS008001DeleteIService {

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
    public int delete(List<Long> listStatusEmployeeID, int companyID) {
        StringBuffer strSql = new StringBuffer();
        strSql.append("UPDATE status_employee se ");
        strSql.append("SET se.is_delete = 1 ");
        strSql.append("WHERE se.status_employee_id IN (:statusEmployeeIDs) ");
        strSql.append("AND se.company_id = :companyID ");
        query = em.createNativeQuery(strSql.toString(), StatusEmployeeEntity.class);
        query.setParameter("statusEmployeeIDs", (Collection<Long>) listStatusEmployeeID);
        query.setParameter("companyID", companyID);
        int updatedQuantity = query.executeUpdate();
        return updatedQuantity;
    }

}
