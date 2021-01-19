package com.brycen.hrm.masterservice.ms020001Delete;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.entity.DegreeEntity;

/**
 * [Description]: This is place to delete Degree<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS020001DeleteImpl implements MS020001DeleteIService {

    @Autowired
    public EntityManager em;

    private Query query;

    @Transactional
    @Override
    public int delete(List<Long> degreeDelete, int companyID) {
        StringBuffer strSql = new StringBuffer();
        strSql.append("UPDATE degree d ");
        strSql.append("SET d.is_delete = 1 ");
        strSql.append("WHERE d.degree_id IN (:degreeIDs) ");
        strSql.append("AND d.company_id = :companyID ");
        query = em.createNativeQuery(strSql.toString(), DegreeEntity.class);
        query.setParameter("degreeIDs", (Collection<Long>) degreeDelete);
        query.setParameter("companyID", companyID);
        int updatedQuantity = query.executeUpdate();
        return updatedQuantity;
    }

}
