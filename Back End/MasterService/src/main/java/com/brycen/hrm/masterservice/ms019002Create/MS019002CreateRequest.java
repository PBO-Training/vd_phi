package com.brycen.hrm.masterservice.ms019002Create;

/**
 * [Description]:<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS019002CreateRequest {

    /**
     * ScopeWork Name
     */
    private String scopeWorkName;

    /**
     * ScopeWork Code
     */
    private String scopeWorkCode;

    /**
     * ScopeWork Value
     */
    private String scopeWorkDescription;

    public String getScopeWorkName() {
        return scopeWorkName;
    }

    public void setScopeWorkName(String scopeWorkName) {
        this.scopeWorkName = scopeWorkName;
    }

    public String getScopeWorkCode() {
        return scopeWorkCode;
    }

    public void setScopeWorkCode(String scopeWorkCode) {
        this.scopeWorkCode = scopeWorkCode;
    }

    public String getScopeWorkDescription() {
        return scopeWorkDescription;
    }

    public void setScopeWorkDescription(String scopeWorkDescription) {
        this.scopeWorkDescription = scopeWorkDescription;
    }

}
