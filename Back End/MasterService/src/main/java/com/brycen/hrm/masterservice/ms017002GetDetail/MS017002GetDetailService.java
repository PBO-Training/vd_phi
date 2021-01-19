package com.brycen.hrm.masterservice.ms017002GetDetail;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]:Interface Get Detail Service for Role-Screen Detail<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS017002GetDetailService {
    /**
     * [Description]: Method get detail Role-Screen with RoleCode<br/>
     * [ Remarks ]:<br/>
     *
     * @param getDetailRequest
     * @param companyID
     * @return
     */
    BaseResponse getDetail(MS017002GetDetailRequest getDetailRequest, int companyID);
}
