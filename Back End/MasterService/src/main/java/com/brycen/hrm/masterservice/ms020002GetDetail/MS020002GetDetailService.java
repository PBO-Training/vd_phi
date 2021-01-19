package com.brycen.hrm.masterservice.ms020002GetDetail;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Interface is called by controller to do actions find a Degree<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS020002GetDetailService {
    /**
     * [Description]: Method find a Degree with id specification<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return MS020002UpdateResponse - Model contain data what need to send to client
     */
    BaseResponse getDetail(long degreeID, int companyID);
}
