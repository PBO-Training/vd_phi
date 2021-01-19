package com.brycen.hrm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brycen.hrm.entity.primaryKey.PrimaryKeyHistoryScopeWork;

/**
 * [Description]:HistoryScopeWorkEntity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "history_scope_work")
@IdClass(PrimaryKeyHistoryScopeWork.class)
public class HistoryScopeWorkEntity {
    /**
     * History ID
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "history_work_id", nullable = false)
    private HistoryWorkEntity historyWork;

    /**
     * Scope work Id
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "scope_work_id", nullable = false)
    private ScopeWorkEntity scopeWork;

    public HistoryScopeWorkEntity() {

    }

    public HistoryScopeWorkEntity(HistoryWorkEntity historyWork, ScopeWorkEntity scopeWork) {
        super();
        this.historyWork = historyWork;
        this.scopeWork = scopeWork;
    }

    public HistoryWorkEntity getHistoryWork() {
        return historyWork;
    }

    public void setHistoryWork(HistoryWorkEntity historyWork) {
        this.historyWork = historyWork;
    }

    public ScopeWorkEntity getScopeWork() {
        return scopeWork;
    }

    public void setScopeWork(ScopeWorkEntity scopeWork) {
        this.scopeWork = scopeWork;
    }

}
