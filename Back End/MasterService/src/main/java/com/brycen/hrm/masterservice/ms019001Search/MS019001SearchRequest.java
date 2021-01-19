package com.brycen.hrm.masterservice.ms019001Search;

import com.brycen.hrm.common.base.BaseRequest;

/**
 * [Description]: Search Request for Scope Work Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS019001SearchRequest extends BaseRequest {

    /**
     * Scope Work name
     */
    private String scopeWorkName;

    /**
     * Scope Work code
     */
    private String scopeWorkCode;

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

}
