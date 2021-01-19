package com.brycen.hrm.masterservice.ms009001Delete;

import java.util.List;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Interface is called by controller to do actions delete a customer<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS009001DeleteService {
    /**
     * [Description]: Method find customers with id specification and delete it<br/>
     * [ Remarks ]:<br/>
     *
     * @param listCustomerID
     * @param companyID
     * @return BaseResponse - Model contain data what need to send to client
     */
    BaseResponse delete(List<Long> listCustomerID, int companyID);
}
