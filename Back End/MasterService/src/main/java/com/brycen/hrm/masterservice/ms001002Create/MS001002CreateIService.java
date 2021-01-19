package com.brycen.hrm.masterservice.ms001002Create;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Create Service for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS001002CreateIService {

    /**
     * [Description]: Create an new user<br/>
     * [ Remarks ]:<br/>
     *
     * @param createUserRequest
     * @param companyID
     * @return content and error status
     */
    public BaseResponse save(MS001002CreateRequest createUserRequest, int companyID);
}
