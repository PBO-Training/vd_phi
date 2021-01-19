package com.brycen.hrm.masterservice.ms012002Update;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.VacationTypeEntity;

/**
 * [Description]: Interface is called by controller to do actions update and find a vacation type<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS012002UpdateService {
    /**
     * [Description]: Method find a vacation type with id specification and update it<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return MS012002UpdateResponse - Model contain data what need to send to client
     */
    BaseResponse save(VacationTypeEntity vacationTypeEntity, int companyID);
}
