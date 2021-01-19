package com.brycen.hrm.masterservice.ms002002Update;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.DepartmentEntity;

/**
 * [Description]: Interface is called by controller to do actions update and find a department<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS002002UpdateService {
    /**
     * [Description]: Method find a department with id specification and update it<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return MS002002UpdateResponse - Model contain data what need to send to client
     */
    BaseResponse save(DepartmentEntity departmentEntity, int companyID);
}
