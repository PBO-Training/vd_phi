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
 * [Description]:PriorityIssue Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "priority_issue")
public class PriorityIssueEntity extends BaseEntity {
    /**
     * Priority Issue Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priority_issue_id")
    private Long priorityIssueID;

    /**
     * Priority Issue Name
     */
    @Column(name = "priority_issue_name", nullable = false)
    private String priorityIssueName;

    /**
     * Priority Issue Code
     */
    @Column(name = "priority_issue_code", length = 40, nullable = false)
    private String priorityIssueCode;   
    
    /**
     * Priority Issue Description
     */
    @Column(name = "priority_issue_description", length = 2000)
    private String priorityIssueDescription;    

    /**
     * List Issue
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "statusIssue")
    private Set<IssueEntity> listIssue = new HashSet<>();
    
    public PriorityIssueEntity() {

    }

    public PriorityIssueEntity(String priorityIssueName, String priorityIssueCode, String priorityIssueDescription) {
        this.priorityIssueName = priorityIssueName;
        this.priorityIssueCode = priorityIssueCode;
        this.priorityIssueDescription = priorityIssueDescription;
    }

    public Long getPriorityIssueID() {
        return priorityIssueID;
    }

    public void setPriorityIssueID(Long priorityIssueID) {
        this.priorityIssueID = priorityIssueID;
    }

    public String getPriorityIssueName() {
        return priorityIssueName;
    }

    public void setPriorityIssueName(String priorityIssueName) {
        this.priorityIssueName = priorityIssueName;
    }

    public String getPriorityIssueCode() {
        return priorityIssueCode;
    }

    public void setPriorityIssueCode(String priorityIssueCode) {
        this.priorityIssueCode = priorityIssueCode;
    }

    public String getPriorityIssueDescription() {
        return priorityIssueDescription;
    }

    public void setPriorityIssueDescription(String priorityIssueDescription) {
        this.priorityIssueDescription = priorityIssueDescription;
    }

    public Set<IssueEntity> getListIssue() {
        return listIssue;
    }

    public void setListIssue(Set<IssueEntity> listIssue) {
        this.listIssue = listIssue;
    }

}
