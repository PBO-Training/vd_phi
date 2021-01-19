package com.brycen.hrm.masterservice.ms007002Update;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.PositionEmployeeEntity;

/**
 * [Description]: Interface is called by controller to do actions update and find a position<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS007002UpdateService {
    /**
     * [Description]: Method find a position with id specification and update it<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return MS007002UpdateResponse - Model contain data what need to send to client
     */
    BaseResponse save(PositionEmployeeEntity positionEmployeeEntity, int companyID);
}
