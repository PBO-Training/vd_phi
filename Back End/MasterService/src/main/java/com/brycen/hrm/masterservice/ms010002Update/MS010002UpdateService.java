package com.brycen.hrm.masterservice.ms010002Update;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.PositionProjectEntity;

/**
 * [Description]: Interface is called by controller to do actions update and find a project position<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS010002UpdateService {
    /**
     * [Description]: Method find a project position with id specification and update it<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return MS010002UpdateResponse - Model contain data what need to send to client
     */
    BaseResponse save(PositionProjectEntity positionProjectEntity, int companyID);
}
