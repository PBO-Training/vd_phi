package com.brycen.hrm.masterservice.ms006001Search;

import com.brycen.hrm.common.base.BaseResponse;

public interface MS006001SearchService {
    /**
     * [Description]: Use jpa to connect database and search levelLanguage with params<br/>
     * [ Remarks ]:<br/>
     *
     * @param searchRequest - A object is created to receive model from client request
     * @return content - MS006001SearchResponse - A object is created to contain data and send to client
     */
    BaseResponse search(MS006001SearchRequest searchRequest, int companyID);
}
