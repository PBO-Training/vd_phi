package com.brycen.hrm.masterservice.ms008002GetDetail;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Get Details Service for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS008002GetDetailIService {

    /**
     * [Description]: Get details a employee status<br/>
     * [ Remarks ]:<br/>
     *
     * @param employeeStatusID
     * @param companyID
     * @return Employee status details
     */
    BaseResponse getDetail(long employeeStatusID, int companyID);

}
