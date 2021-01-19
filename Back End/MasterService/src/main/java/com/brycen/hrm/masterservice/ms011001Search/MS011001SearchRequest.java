package com.brycen.hrm.masterservice.ms011001Search;

import com.brycen.hrm.common.base.BaseRequest;

/**
 * [Description]:Object contain field to receive data from request<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS011001SearchRequest extends BaseRequest {
    /**
     * levelProject code
     */
    private String levelProjectCode;
    /**
     * levelProject name
     */
    private String levelProjectName;

    public MS011001SearchRequest() {
        super();
    }

    public MS011001SearchRequest(String levelProjectCode, String levelProjectName) {
        super();
        this.levelProjectCode = levelProjectCode;
        this.levelProjectName = levelProjectName;
    }

    public String getLevelProjectCode() {
        return levelProjectCode;
    }

    public void setLevelProjectCode(String levelProjectCode) {
        this.levelProjectCode = levelProjectCode;
    }

    public String getLevelProjectName() {
        return levelProjectName;
    }

    public void setLevelProjectName(String levelProjectName) {
        this.levelProjectName = levelProjectName;
    }
}
