package com.brycen.hrm.masterservice.ms019002Update;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.ScopeWorkEntity;

/**
 * [Description]: Update Service for Scope Work Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS019002UpdateIService {

    /**
     * [Description]: Save a new scope work details<br/>
     * [ Remarks ]:<br/>
     *
     * @param scopeWorkEntity
     * @param companyID
     * @return Content and error status
     */
    BaseResponse save(ScopeWorkEntity scopeWorkEntity, int companyID);

}
