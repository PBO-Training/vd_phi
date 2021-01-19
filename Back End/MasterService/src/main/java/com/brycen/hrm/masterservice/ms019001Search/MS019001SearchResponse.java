package com.brycen.hrm.masterservice.ms019001Search;

import com.brycen.hrm.entity.ScopeWorkEntity;

/**
 * [Description]: Search Response for Scope Work Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS019001SearchResponse {

    /**
     * Scope Work ID
     */
    private Long scopeWorkID;

    /**
     * Scope Work name
     */
    private String scopeWorkName;

    /**
     * Scope Work code
     */
    private String scopeWorkCode;

    /**
     * Scope Work Description
     */
    private String scopeWorkDescription;

    public MS019001SearchResponse(ScopeWorkEntity ScopeWorkEntity) {
        super();
        this.scopeWorkID = ScopeWorkEntity.getScopeWorkID();
        this.scopeWorkCode = ScopeWorkEntity.getScopeWorkCode();
        this.scopeWorkName = ScopeWorkEntity.getScopeWorkName();
        this.scopeWorkDescription = ScopeWorkEntity.getScopeWorkDescription();
    }

    public Long getScopeWorkID() {
        return scopeWorkID;
    }

    public void setScopeWorkID(Long scopeWorkID) {
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
