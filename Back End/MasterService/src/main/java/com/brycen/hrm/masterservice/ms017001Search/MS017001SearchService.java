package com.brycen.hrm.masterservice.ms017001Search;

import com.brycen.hrm.common.base.BaseResponse;

public interface MS017001SearchService {
    /**
     * [Description]: Use query to connect database and search role screen type with params<br/>
     * [ Remarks ]:<br/>
     *
     * @param searchRequest - A object is created to receive model from client request
     * @return content - MS017001SearchRequest - A object is created to contain data and send to client
     */
    BaseResponse search(MS017001SearchRequest searchRequest, int companyID);
}
