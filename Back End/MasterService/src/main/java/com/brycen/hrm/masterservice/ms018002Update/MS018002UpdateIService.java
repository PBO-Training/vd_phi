package com.brycen.hrm.masterservice.ms018002Update;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.RoleEntity;

/**
 * [Description]: Update Service for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS018002UpdateIService {

    /**
     * [Description]: Save a new role details<br/>
     * [ Remarks ]:<br/>
     *
     * @param roleEntity
     * @param companyID
     * @return Content and error status
     */
    BaseResponse save(RoleEntity roleEntity, int companyID);

}
