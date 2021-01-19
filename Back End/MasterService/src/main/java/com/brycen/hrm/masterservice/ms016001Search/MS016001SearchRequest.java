package com.brycen.hrm.masterservice.ms016001Search;

import com.brycen.hrm.common.base.BaseRequest;

/**
 * [Description]:Object contain field to receive data from request<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS016001SearchRequest extends BaseRequest {
    /**
     * EvaluateProject code
     */
    private String evaluateProjectCode;
    /**
     * EvaluateProject name
     */
    private String evaluateProjectName;

    public MS016001SearchRequest() {
        super();
    }

    public MS016001SearchRequest(String evaluateProjectCode, String evaluateProjectName) {
        super();
        this.evaluateProjectCode = evaluateProjectCode;
        this.evaluateProjectName = evaluateProjectName;
    }

    public String getEvaluateProjectCode() {
        return evaluateProjectCode;
    }

    public void setEvaluateProjectCode(String evaluateProjectCode) {
        this.evaluateProjectCode = evaluateProjectCode;
    }

    public String getEvaluateProjectName() {
        return evaluateProjectName;
    }

    public void setEvaluateProjectName(String evaluateProjectName) {
        this.evaluateProjectName = evaluateProjectName;
    }
}
