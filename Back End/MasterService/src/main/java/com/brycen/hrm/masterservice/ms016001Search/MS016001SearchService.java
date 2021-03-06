package com.brycen.hrm.masterservice.ms016001Search;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Interface is called by controller to do actions search<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS016001SearchService {
    /**
     * [Description]: Use query to connect database and search evaluateProject with params<br/>
     * [ Remarks ]:<br/>
     *
     * @param MS016001SearchRequest - A object is created to receive model from client request
     * @param companyID             - int
     * @return BaseResponse - A object contain data with sturcture is defined and send to client
     */
    BaseResponse search(MS016001SearchRequest searchRequest, int companyID);
}
