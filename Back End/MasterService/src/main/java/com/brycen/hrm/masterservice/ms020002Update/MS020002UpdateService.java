package com.brycen.hrm.masterservice.ms020002Update;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.DegreeEntity;

/**
 * [Description]: Interface is called by controller to do actions update and find a degree<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS020002UpdateService {
    /**
     * [Description]: Method find a degree with id specification and update it<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return MS020002UpdateResponse - Model contain data what need to send to client
     */
    BaseResponse save(DegreeEntity degreeEntity, int companyID);
}
