package com.brycen.hrm.masterservice.ms012001Delete;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.entity.VacationTypeEntity;

/**
 * [Description]: This is place to delete vacation type<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS012001DeleteImpl implements MS012001DeleteIService {

    @Autowired
    public EntityManager em;

    private Query query;

    @Transactional
    @Override
    public int delete(List<Long> vacationTypeDelete, int companyID) {
        StringBuffer strSql = new StringBuffer();
        strSql.append("UPDATE vacation_type v ");
        strSql.append("SET v.is_delete = 1 ");
        strSql.append("WHERE v.vacation_type_id IN (:vacationTypeIDs) ");
        strSql.append("AND v.company_id = :companyID ");
        query = em.createNativeQuery(strSql.toString(), VacationTypeEntity.class);
        query.setParameter("vacationTypeIDs", (Collection<Long>) vacationTypeDelete);
        query.setParameter("companyID", companyID);
        int updatedQuantity = query.executeUpdate();
        return updatedQuantity;
    }

}
