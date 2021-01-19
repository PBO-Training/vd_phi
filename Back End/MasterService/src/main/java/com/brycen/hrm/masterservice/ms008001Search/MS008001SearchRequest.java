package com.brycen.hrm.masterservice.ms008001Search;

import com.brycen.hrm.common.base.BaseRequest;
import com.brycen.hrm.entity.StatusEmployeeEntity;

/**
 * [Description]: Search Request for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS008001SearchRequest extends BaseRequest {

    private String statusEmployeeName;
    private String statusEmployeeCode;

    public MS008001SearchRequest() {
        super();
    }

    public MS008001SearchRequest(StatusEmployeeEntity statusEmployeeEntity) {
        super();
        this.statusEmployeeName = statusEmployeeEntity.getStatusEmployeeCode();
        this.statusEmployeeCode = statusEmployeeEntity.getStatusEmployeeName();
    }

    public String getStatusEmployeeName() {
        return statusEmployeeName;
    }

    public void setStatusEmployeeName(String statusEmployeeName) {
        this.statusEmployeeName = statusEmployeeName;
    }

    public String getStatusEmployeeCode() {
        return statusEmployeeCode;
    }

    public void setStatusEmployeeCode(String statusEmployeeCode) {
        this.statusEmployeeCode = statusEmployeeCode;
    }

}
