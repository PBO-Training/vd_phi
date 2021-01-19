package com.brycen.hrm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:Table contain history of issue<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "history_update_issue")
public class HistoryUpdateIssue extends BaseEntity {
    /**
     * ID of history issue updated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_update_issue_id")
    private Long historyUpdateIssueID;
    /**
     * Code of history issue updated
     */
    private String historyUpdateIssueCode;
    /**
     * The Time update issue
     */
    private Date historyUpdateIssueTime;
    /**
     * Id of issue contain history
     */
    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    private IssueEntity issue;
    /**
     * Id of employee updated issue
     */
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;
    /**
     * List field updated
     */
    @OneToMany(mappedBy = "historyUpdateIssue", cascade = CascadeType.ALL)
    private List<FieldIssueUpdate> fieldIssueUpdate;

    public HistoryUpdateIssue() {
        super();
    }

    public Long getHistoryUpdateIssueID() {
        return historyUpdateIssueID;
    }

    public void setHistoryUpdateIssueID(Long historyUpdateIssueID) {
        this.historyUpdateIssueID = historyUpdateIssueID;
    }

    public String getHistoryUpdateIssueCode() {
        return historyUpdateIssueCode;
    }

    public void setHistoryUpdateIssueCode(String historyUpdateIssueCode) {
        this.historyUpdateIssueCode = historyUpdateIssueCode;
    }

    public Date getHistoryUpdateIssueTime() {
        return historyUpdateIssueTime;
    }

    public void setHistoryUpdateIssueTime(Date historyUpdateIssueTime) {
        this.historyUpdateIssueTime = historyUpdateIssueTime;
    }

    public IssueEntity getIssue() {
        return issue;
    }

    public void setIssue(IssueEntity issue) {
        this.issue = issue;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public List<FieldIssueUpdate> getFieldIssueUpdate() {
        return fieldIssueUpdate;
    }

    public void setFieldIssueUpdate(List<FieldIssueUpdate> fieldIssueUpdate) {
        this.fieldIssueUpdate = fieldIssueUpdate;
    }
}
