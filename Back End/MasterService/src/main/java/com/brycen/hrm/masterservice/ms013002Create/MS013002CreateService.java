package com.brycen.hrm.masterservice.ms013002Create;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Create Service for Holiday Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS013002CreateService {

    /**
     * [Description]: Create a new holiday<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms013002CreateRequest
     * @param companyID
     * @return Content and error service
     */
    BaseResponse save(MS013002CreateRequest ms013002CreateRequest, int companyID);

}
