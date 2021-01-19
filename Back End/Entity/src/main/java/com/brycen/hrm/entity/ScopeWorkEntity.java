package com.brycen.hrm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:ProcessProject Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "scope_work")
public class ScopeWorkEntity extends BaseEntity {
    /**
     * Scope work Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scope_work_id")
    private Long scopeWorkID;

    /**
     * Scope work Name
     */
    @Column(name = "scope_work_name", nullable = false)
    private String scopeWorkName;

    /**
     * Scope work Code
     */
    @Column(name = "scope_work_code", nullable = false, length = 40)
    private String scopeWorkCode;

    /**
     * Scope work Description
     */
    @Column(name = "scope_work_description", length = 2000)
    private String scopeWorkDescription;

    /**
     * List History Process Project
     */
    @OneToMany(mappedBy = "scopeWork", cascade = CascadeType.ALL)
    private List<HistoryScopeWorkEntity> listHistoryScopeWork;

    public ScopeWorkEntity() {

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

    public List<HistoryScopeWorkEntity> getHistoryProcessProject() {
        return listHistoryScopeWork;
    }

    public void setHistoryProcessProject(List<HistoryScopeWorkEntity> listHistoryScopeWork) {
        this.listHistoryScopeWork = listHistoryScopeWork;
    }

}
