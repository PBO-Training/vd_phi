package com.brycen.hrm.masterservice.ms022001Search;

import com.brycen.hrm.common.base.BaseRequest;

/**
 * [Description]: Modal contain data need to receive from client to search<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS022001SearchRequest extends BaseRequest {
    /**
     * Code of skill project
     */
    private String skillProjectCode;
    /**
     * Name of skill project
     */
    private String skillProjectName;

    public MS022001SearchRequest() {
        super();
    }

    public MS022001SearchRequest(String skillProjectCode, String skillProjectName) {
        super();
        this.skillProjectCode = skillProjectCode;
        this.skillProjectName = skillProjectName;
    }

    public String getSkillProjectCode() {
        return skillProjectCode;
    }

    public void setSkillProjectCode(String skillProjectCode) {
        this.skillProjectCode = skillProjectCode;
    }

    public String getSkillProjectName() {
        return skillProjectName;
    }

    public void setSkillProjectName(String skillProjectName) {
        this.skillProjectName = skillProjectName;
    }
}
