package com.brycen.hrm.masterservice.ms018002Create;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Create Service for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS018002CreateIService {

    /**
     * [Description]: Create a new role<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms018002CreateRequest
     * @param companyID
     * @return Content and error status
     */
    BaseResponse save(MS018002CreateRequest ms018002CreateRequest, int companyID);

}
