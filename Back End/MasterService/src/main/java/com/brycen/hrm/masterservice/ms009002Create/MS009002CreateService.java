package com.brycen.hrm.masterservice.ms009002Create;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.CustomerEntity;

/**
 * [Description]: Interface is called by controller to do actions create<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS009002CreateService {
    /**
     * [Description]: Method create new customer<br/>
     * [ Remarks ]:<br/>
     *
     * @param customerEntity
     */
    BaseResponse save(CustomerEntity customerEntity, int companyID);
}
