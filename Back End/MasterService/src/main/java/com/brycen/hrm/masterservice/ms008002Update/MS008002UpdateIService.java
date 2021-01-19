package com.brycen.hrm.masterservice.ms008002Update;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.StatusEmployeeEntity;

/**
 * [Description]: Update Service for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS008002UpdateIService {

    /**
     * [Description]: Update an employee status<br/>
     * [ Remarks ]:<br/>
     *
     * @param statusEmployeeEntity
     * @param companyID
     * @return Content and error status
     */
    BaseResponse save(StatusEmployeeEntity statusEmployeeEntity, int companyID);

}
