package com.brycen.hrm.masterservice.ms009001Delete;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.CustomerEntity;

/**
 * [Description]: This is place to delete customer<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS009001DeleteImpl implements MS009001DeleteService {
    @Autowired
    public EntityManager em;

    private Query query;

    @Transactional
    @Override
    public BaseResponse delete(List<Long> listCustomerID, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        StringBuffer strSql = new StringBuffer();
        strSql.append("UPDATE customer c ");
        strSql.append("SET c.is_delete = 1 ");
        strSql.append("WHERE c.customer_id IN (:customerIDs) ");
        strSql.append("AND c.company_id = :companyID ");
        query = em.createNativeQuery(strSql.toString(), CustomerEntity.class);
        query.setParameter("customerIDs", (Collection<Long>) listCustomerID);
        query.setParameter("companyID", companyID);
        query.executeUpdate();
        return baseRes;
    }
}
