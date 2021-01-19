package com.brycen.hrm.masterservice.ms012002Create;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.VacationTypeEntity;

/**
 * [Description]: Interface is called by controller to do actions create<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS012002CreateService {
    /**
     * [Description]: Method create new vacation type<br/>
     * [ Remarks ]:<br/>
     *
     * @param vacationTypeEntity
     */
    BaseResponse save(VacationTypeEntity vacationTypeEntity, int companyID);
}
