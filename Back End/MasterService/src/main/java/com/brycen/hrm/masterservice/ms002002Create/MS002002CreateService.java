package com.brycen.hrm.masterservice.ms002002Create;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.DepartmentEntity;

/**
 * [Description]: Interface is called by controller to do actions create<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS002002CreateService {
    /**
     * [Description]: Method create new department<br/>
     * [ Remarks ]:<br/>
     *
     * @param departmentEntity
     */
    BaseResponse save(DepartmentEntity departmentEntity, int companyID);
}
