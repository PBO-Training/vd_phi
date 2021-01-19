package com.brycen.hrm.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:StatusIssue Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "status_issue")
public class StatusIssueEntity extends BaseEntity{
    /**
     * Status Issue Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_issue_id")
    private Long statusIssueID;

    /**
     * Status Issue Name
     */
    @Column(name = "status_issue_name", nullable = false)
    private String statusIssueName;

    /**
     * Status Issue Code
     */
    @Column(name = "status_issue_code", nullable = false, length = 40)
    private String statusIssueCode;
    
    /**
     * Status Issue Description
     */
    @Column(name = "status_issue_description", length = 2000)
    private String statusIssueDescription;    

    /**
     * List Issue
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "statusIssue")
    private Set<IssueEntity> listIssue = new HashSet<>();
    
    public StatusIssueEntity() {

    }

    public StatusIssueEntity(String statusIssueName, String statusIssueCode, String statusIssueDescription) {
        this.statusIssueName = statusIssueName;
        this.statusIssueCode = statusIssueCode;
        this.statusIssueDescription = statusIssueDescription;
    }

    public Long getStatusIssueID() {
        return statusIssueID;
    }

    public void setStatusIssueID(Long statusIssueID) {
        this.statusIssueID = statusIssueID;
    }

    public String getStatusIssueName() {
        return statusIssueName;
    }

    public void setStatusIssueName(String statusIssueName) {
        this.statusIssueName = statusIssueName;
    }

    public String getStatusIssueCode() {
        return statusIssueCode;
    }

    public void setStatusIssueCode(String statusIssueCode) {
        this.statusIssueCode = statusIssueCode;
    }

    public String getStatusIssueDescription() {
        return statusIssueDescription;
    }

    public void setStatusIssueDescription(String statusIssueDescription) {
        this.statusIssueDescription = statusIssueDescription;
    }

    public Set<IssueEntity> getListIssue() {
        return listIssue;
    }

    public void setListIssue(Set<IssueEntity> listIssue) {
        this.listIssue = listIssue;
    }
    
}
