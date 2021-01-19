package com.brycen.hrm.masterservice.ms010002Create;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.PositionProjectEntity;

/**
 * [Description]: Interface is called by controller to do actions create<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS010002CreateService {
    /**
     * [Description]: Method create new project position<br/>
     * [ Remarks ]:<br/>
     *
     * @param projectPositionEntity
     */
    BaseResponse save(PositionProjectEntity positionProjectEntity, int companyID);
}
