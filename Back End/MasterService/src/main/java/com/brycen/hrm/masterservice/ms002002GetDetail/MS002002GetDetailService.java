package com.brycen.hrm.masterservice.ms002002GetDetail;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Interface is called by controller to do actions find a department<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS002002GetDetailService {
    /**
     * [Description]: Method find a department with id specification<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return MS002002UpdateResponse - Model contain data what need to send to client
     */
    BaseResponse getDetail(long departmentID, int companyID);
}
