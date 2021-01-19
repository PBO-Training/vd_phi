package com.brycen.hrm.masterservice.ms007001Delete;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.entity.PositionEmployeeEntity;

/**
 * [Description]: This is place to delete employee position<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS007001DeleteImpl implements MS007001DeleteIService {

    @Autowired
    public EntityManager em;

    private Query query;

    @Transactional
    @Override
    public int delete(List<Long> listEmployeePositionDeleteID, int companyID) {
        // TODO Auto-generated method stub
        StringBuffer strSql = new StringBuffer();
        strSql.append("UPDATE position_employee p ");
        strSql.append("SET p.is_delete = 1 ");
        strSql.append("WHERE p.position_employee_id IN (:positionEmployeeIDs) ");
        strSql.append("AND p.company_id = :companyID ");
        query = em.createNativeQuery(strSql.toString(), PositionEmployeeEntity.class);
        query.setParameter("positionEmployeeIDs", (Collection<Long>) listEmployeePositionDeleteID);
        query.setParameter("companyID", companyID);

        int updatedQuantity = query.executeUpdate();
        return updatedQuantity;

    }

}
