package com.brycen.hrm.masterservice.ms007002GetDetail;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Interface is called by controller to do actions find a employee position<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS007002GetDetailService {
    /**
     * [Description]: Method find a position with id specification<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return MS007002UpdateResponse - Model contain data what need to send to client
     */
    BaseResponse getDetail(long positionEmployeeID, int companyID);
}
