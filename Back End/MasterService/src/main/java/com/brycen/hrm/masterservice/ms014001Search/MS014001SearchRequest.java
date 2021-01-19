package com.brycen.hrm.masterservice.ms014001Search;

import com.brycen.hrm.common.base.BaseRequest;

/**
 * [Description]:Object contain field to receive data from request<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS014001SearchRequest extends BaseRequest {
    /**
     * statusProject code
     */
    private String statusProjectCode;
    /**
     * statusProject name
     */
    private String statusProjectName;

    public MS014001SearchRequest() {
        super();
    }

    public MS014001SearchRequest(String statusProjectCode, String statusProjectName) {
        super();
        this.statusProjectCode = statusProjectCode;
        this.statusProjectName = statusProjectName;
    }

    public String getStatusProjectCode() {
        return statusProjectCode;
    }

    public void setStatusProjectCode(String statusProjectCode) {
        this.statusProjectCode = statusProjectCode;
    }

    public String getStatusProjectName() {
        return statusProjectName;
    }

    public void setStatusProjectName(String statusProjectName) {
        this.statusProjectName = statusProjectName;
    }
}
