package com.brycen.hrm.masterservice.ms020002Create;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.DegreeEntity;

/**
 * [Description]: Interface is called by controller to do actions create<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS020002CreateService {
    /**
     * [Description]: Method create new vacation type<br/>
     * [ Remarks ]:<br/>
     *
     * @param degreeEntity
     */
    BaseResponse save(DegreeEntity degreeEntity, int companyID);
}
