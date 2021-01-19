package com.brycen.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:Table contain fields is updated in issue<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "field_issue_update")
public class FieldIssueUpdate extends BaseEntity {
    /**
     * ID of field updated in history issue
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_issue_update_id")
    private Long fieldIssueUpdateID;
    /**
     * Name of field updated in history issue
     */
    private String fieldIssueUpdateName;
    /**
     * Previous value of issue
     */
    private String fieldIssueUpdateFrom;
    
    /**
     * Value is updated in history issue update
     */
    private String fieldIssueUpdateTo;
    /**
     * Id of history contain field
     */
    @ManyToOne
    @JoinColumn(name = "history_update_issue_id", nullable = false)
    private HistoryUpdateIssue historyUpdateIssue;

    public FieldIssueUpdate() {
        super();
    }

    public Long getFieldIssueUpdateID() {
        return fieldIssueUpdateID;
    }

    public void setFieldIssueUpdateID(Long fieldIssueUpdateID) {
        this.fieldIssueUpdateID = fieldIssueUpdateID;
    }

    public String getFieldIssueUpdateName() {
        return fieldIssueUpdateName;
    }

    public void setFieldIssueUpdateName(String fieldIssueUpdateName) {
        this.fieldIssueUpdateName = fieldIssueUpdateName;
    }

    public String getFieldIssueUpdateFrom() {
        return fieldIssueUpdateFrom;
    }

    public void setFieldIssueUpdateFrom(String fieldIssueUpdateFrom) {
        this.fieldIssueUpdateFrom = fieldIssueUpdateFrom;
    }

    public String getFieldIssueUpdateTo() {
        return fieldIssueUpdateTo;
    }

    public void setFieldIssueUpdateTo(String fieldIssueUpdateTo) {
        this.fieldIssueUpdateTo = fieldIssueUpdateTo;
    }

    public HistoryUpdateIssue getHistoryUpdateIssue() {
        return historyUpdateIssue;
    }

    public void setHistoryUpdateIssue(HistoryUpdateIssue historyUpdateIssue) {
        this.historyUpdateIssue = historyUpdateIssue;
    }
}
