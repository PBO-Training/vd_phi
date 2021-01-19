package com.brycen.hrm.masterservice.ms001001Init;

import com.brycen.hrm.common.base.BaseInitResponse;
import com.brycen.hrm.entity.DepartmentEntity;

/**
 * [Description]: Department response<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS001001InitDepartmentResponse extends BaseInitResponse {

    public MS001001InitDepartmentResponse() {
    }

    public MS001001InitDepartmentResponse(DepartmentEntity departmentEntity) {
        this.setKeyResponse(departmentEntity.getDepartmentID());
        this.setValueResponse(departmentEntity.getDepartmentName());
    }

    public MS001001InitDepartmentResponse(Long departmentID, String departmentName) {
        this.setKeyResponse(departmentID);
        this.setValueResponse(departmentName);
    }

}
