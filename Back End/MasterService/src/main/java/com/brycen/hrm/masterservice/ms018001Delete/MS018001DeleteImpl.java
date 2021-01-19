package com.brycen.hrm.masterservice.ms018001Delete;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.RoleEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Delete Service Implementation for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS018001DeleteImpl implements MS018001DeleteIService {

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

    int countUser(List<Long> listDelete, int companyID) {
        StringBuffer strSql = new StringBuffer();
        strSql.append("SELECT COUNT(user_id) ");
        strSql.append("FROM user u ");
        strSql.append("WHERE u.company_id = :companyID AND u.is_delete = 0 AND u.role_id IN (:roleID)");
        query = em.createNativeQuery(strSql.toString());
        query.setParameter("companyID", companyID);
        query.setParameter("roleID", (Collection<Long>) listDelete);
        int totalUser = ((Number) query.getSingleResult()).intValue();
        return totalUser;
    }

    public ErrorResponse checkValue(List<Long> listDelete, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        if (countUser(listDelete, companyID) > 0) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_ROLE)
                    .append(ErrorValue.API_DELETE_LIST_ROLE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("listDelete");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Transactional
    public BaseResponse delete(List<Long> listDelete, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        ErrorResponse error = checkValue(listDelete, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS018001_DELETE_GROUP_ROLE, listDelete, baseRes, "");
            return baseRes;
        }
        StringBuffer strSql = new StringBuffer();
        strSql.append("UPDATE role r ");
        strSql.append("SET r.is_delete = 1 ");
        strSql.append("WHERE r.role_ID IN (:roleID) ");
        strSql.append("AND r.company_id = :companyID ");
        query = em.createNativeQuery(strSql.toString(), RoleEntity.class);
        query.setParameter("roleID", (Collection<Long>) listDelete);
        query.setParameter("companyID", companyID);
        query.executeUpdate();
        return baseRes;
    }

}
