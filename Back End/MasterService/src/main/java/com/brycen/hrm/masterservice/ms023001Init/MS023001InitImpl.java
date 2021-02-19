package com.brycen.hrm.masterservice.ms023001Init;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.SystemSettingEntity;

/**
 * [Description]:<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS023001InitImpl implements MS023001InitIService {

    /**
     * Call entity manager to support creation a native query statement
     */
    @Autowired
    public EntityManager em;

    /**
     * Call query to save an instance native query statement
     */
    private Query query;

    @Override
    public BaseResponse init( int companyID) {
    	BaseResponse baseRes = new BaseResponse();
    	StringBuffer strSql = new StringBuffer();
    	strSql.append("SELECT * FROM system_setting ");
    	strSql.append(" WHERE company_id = :companyID ");
    	query = em.createNativeQuery(strSql.toString(),SystemSettingEntity.class);
    	query.setParameter("companyID", companyID);
    	List<SystemSettingEntity> step = query.getResultList();
    	List<Object> result = step.stream().map(MS023001InitResponse::new).collect(Collectors.toList());
    	baseRes.setContent(result);
        return baseRes;
    }

}
