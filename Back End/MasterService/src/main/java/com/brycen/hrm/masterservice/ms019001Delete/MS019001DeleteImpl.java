package com.brycen.hrm.masterservice.ms019001Delete;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.ScopeWorkEntity;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Delete Service Implementation for Scope Work Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS019001DeleteImpl implements MS019001DeleteIService {

    /**
     * Call entity manager to support creation a native query statement
     */
    @Autowired
    public EntityManager em;

    /**
     * Call query to save an instance native query statement
     */
    private Query query;

    /**
     * Write log
     */
    @Autowired
    LoggerService logger;

    @Transactional
    public BaseResponse delete(List<Long> listDelete, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        StringBuffer strSql = new StringBuffer();
        strSql.append("UPDATE scope_work s ");
        strSql.append("SET s.is_delete = 1 ");
        strSql.append("WHERE s.scope_work_ID IN (:scopeWorkID) ");
        strSql.append("AND s.company_id = :companyID ");
        query = em.createNativeQuery(strSql.toString(), ScopeWorkEntity.class);
        query.setParameter("scopeWorkID", (Collection<Long>) listDelete);
        query.setParameter("companyID", companyID);
        query.executeUpdate();
        return baseRes;
    }

}
