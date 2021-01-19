package com.brycen.hrm.masterservice.ms009002Update;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.CustomerEntity;

/**
 * [Description]: Interface is called by controller to do actions update and find a customer<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS009002UpdateService {
    /**
     * [Description]: Method find a customer with id specification and update it<br/>
     * [ Remarks ]:<br/>
     *
     * @param customerEntity
     * @param companyID
     * @return BaseResponse - Model contain data what need to send to client
     */
    BaseResponse save(CustomerEntity customerEntity, int companyID);
}
