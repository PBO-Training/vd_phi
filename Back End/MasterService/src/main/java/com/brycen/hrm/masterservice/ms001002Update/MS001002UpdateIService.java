package com.brycen.hrm.masterservice.ms001002Update;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Update Service for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS001002UpdateIService {

    /**
     * [Description]: Update an user<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms001002UpdateRequest
     * @param companyID
     * @return Content and error status
     */
    BaseResponse updateUser(@RequestBody MS001002UpdateRequest ms001002UpdateRequest, int companyID);

}
