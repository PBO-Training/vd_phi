package com.brycen.hrm.masterservice.ms019002GetDetail;

import com.brycen.hrm.entity.ScopeWorkEntity;

/**
 * [Description]:<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS019002GetDetailResponse {

    /**
     * ScopeWork ID
     */
    private long scopeWorkID;

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

    public MS019002GetDetailResponse() {
        super();
    }

    public MS019002GetDetailResponse(ScopeWorkEntity scopeWorkEntity) {
        super();
        this.scopeWorkID = scopeWorkEntity.getScopeWorkID();
        this.scopeWorkName = scopeWorkEntity.getScopeWorkName();
        this.scopeWorkCode = scopeWorkEntity.getScopeWorkCode();
        this.scopeWorkDescription = scopeWorkEntity.getScopeWorkDescription();
    }

    public long getScopeWorkID() {
        return scopeWorkID;
    }

    public void setScopeWorkID(long scopeWorkID) {
        this.scopeWorkID = scopeWorkID;
    }

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
