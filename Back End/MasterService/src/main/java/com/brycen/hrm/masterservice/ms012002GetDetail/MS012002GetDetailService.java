package com.brycen.hrm.masterservice.ms012002GetDetail;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Interface is called by controller to do actions find a vacation type<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS012002GetDetailService {
    /**
     * [Description]: Method find a vacation type with id specification<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return MS012002UpdateResponse - Model contain data what need to send to client
     */
    BaseResponse getDetail(long vacationTypeID, int companyID);
}
