package com.brycen.hrm.masterservice.ms013002Update;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Interface is called by controller to do actions update and find a holiday<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS013002UpdateService {
    /**
     * [Description]: Method find a holiday with id specification and update it<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return BaseResponse - Model contain data what need to send to client
     */
    BaseResponse save(MS013002UpdateRequest request, int companyID);
}
