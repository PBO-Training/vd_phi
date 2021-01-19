package com.brycen.hrm.masterservice.ms010001Search;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Interface is called by controller to do actions search<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS010001SearchService {
    /**
     * [Description]: Use query to connect database and search positionProject with params<br/>
     * [ Remarks ]:<br/>
     *
     * @param searchRequest - A object is created to receive model from client request
     * @return content - MS010001SearchResponse - A object contain data and send to client
     */
    BaseResponse search(MS010001SearchRequest searchRequest, int companyID);
}
