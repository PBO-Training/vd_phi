package com.brycen.hrm.masterservice.ms007001Search;

import com.brycen.hrm.common.base.BaseRequest;

/**
 * [Description]: Modal contain data need to receive from client to search<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS007001SearchRequest extends BaseRequest {
    private String positionEmployeeName;
    private String positionEmployeeCode;

    public MS007001SearchRequest(String positionEmployeeName, String positionEmployeeCode) {
        super();
        this.positionEmployeeName = positionEmployeeName;
        this.positionEmployeeCode = positionEmployeeCode;
    }

    public String getPositionEmployeeName() {
        return positionEmployeeName;
    }

    public void setPositionEmployeeName(String positionEmployeeName) {
        this.positionEmployeeName = positionEmployeeName;
    }

    public String getPositionEmployeeCode() {
        return positionEmployeeCode;
    }

    public void setPositionEmployeeCode(String positionEmployeeCode) {
        this.positionEmployeeCode = positionEmployeeCode;
    }

}
