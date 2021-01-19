package com.brycen.hrm.masterservice.ms005001Search;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Interface is called by controller to do actions search<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS005001SearchService {
    BaseResponse search(MS005001SearchRequest searchRequest, int companyID);
}
