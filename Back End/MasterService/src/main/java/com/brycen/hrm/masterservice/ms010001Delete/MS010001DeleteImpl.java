package com.brycen.hrm.masterservice.ms010001Delete;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.entity.PositionProjectEntity;

/**
 * [Description]: This is place to delete project position<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS010001DeleteImpl implements MS010001DeleteIService {

    @Autowired
    public EntityManager em;

    private Query query;

    @Transactional
    @Override
    public int delete(List<Long> positionDelete, int companyID) {
        // TODO Auto-generated method stub
        StringBuffer strSql = new StringBuffer();
        strSql.append("UPDATE position_project p ");
        strSql.append("SET p.is_delete = 1 ");
        strSql.append("WHERE p.position_project_id IN (:positionProjectIDs) ");
        strSql.append("AND p.company_id = :companyID ");
        query = em.createNativeQuery(strSql.toString(), PositionProjectEntity.class);
        query.setParameter("positionProjectIDs", (Collection<Long>) positionDelete);
        query.setParameter("companyID", companyID);
        int updatedQuantity = query.executeUpdate();
        return updatedQuantity;
    }

}
