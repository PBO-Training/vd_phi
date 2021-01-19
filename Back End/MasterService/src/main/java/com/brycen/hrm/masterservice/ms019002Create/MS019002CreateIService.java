package com.brycen.hrm.masterservice.ms019002Create;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Create Service for Scope Work Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS019002CreateIService {

    /**
     * [Description]: Create a new scope work<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms018002CreateRequest
     * @param companyID
     * @return Content and error status
     */
    BaseResponse save(MS019002CreateRequest ms019002CreateRequest, int companyID);

}
