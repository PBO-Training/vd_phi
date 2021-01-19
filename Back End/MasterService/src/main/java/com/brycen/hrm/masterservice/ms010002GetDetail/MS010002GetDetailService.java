package com.brycen.hrm.masterservice.ms010002GetDetail;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Interface is called by controller to do actions find a project position<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS010002GetDetailService {
    /**
     * [Description]: Method find a project position with id specification<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return MS010002UpdateResponse - Model contain data what need to send to client
     */
    BaseResponse getDetail(long positionProjectID, int companyID);
}
