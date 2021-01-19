package com.brycen.hrm.masterservice.ms007002Create;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.PositionEmployeeEntity;

/**
 * [Description]: Interface is called by controller to do actions create<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS007002CreateService {
    /**
     * [Description]: Method create new employee position<br/>
     * [ Remarks ]:<br/>
     *
     * @param positionEmployeeEntity
     */
    BaseResponse save(PositionEmployeeEntity positionEmployeeEntity, int companyID);
}
