package com.brycen.hrm.masterservice.ms003002Update;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Interface is called by controller to do actions update and find a skill<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS003002UpdateService {
    /**
     * [Description]: Method find a skill with id specification and update it<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return MS003002UpdateResponse - Model contain data what need to send to client
     */
    BaseResponse save(MS003002UpdateRequest request, int companyID);
}
