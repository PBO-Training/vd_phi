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
 * [Description]:FileIssue Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "file_issue")
public class FileIssueEntity extends BaseEntity{
    /**
     * File Issue Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_issue_id")
    private Long fileIssueID;

    /**
     * File Issue Name
     */
    @Column(name = "file_issue_name", nullable = false)
    private String fileIssueName;

    /**
     * File Issue Type
     */
    @Column(name = "file_issue_type")
    private String fileIssueType;
    
    /**
     * File Issue Url
     */
    @Column(name = "file_issue_url", nullable = false, length = 2000)
    private String fileIssueUrl;

    /**
     * Issue Id
     */
    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    private IssueEntity issue;
    
    public FileIssueEntity() {

    }

    public FileIssueEntity(String fileIssueName, String fileIssueType, String fileIssueUrl) {
        this.fileIssueName = fileIssueName;
        this.fileIssueType = fileIssueType;
        this.fileIssueUrl = fileIssueUrl;
    }

    public Long getFileIssueID() {
        return fileIssueID;
    }

    public void setFileIssueID(Long fileIssueID) {
        this.fileIssueID = fileIssueID;
    }

    public String getFileIssueName() {
        return fileIssueName;
    }

    public void setFileIssueName(String fileIssueName) {
        this.fileIssueName = fileIssueName;
    }

    public String getFileIssueType() {
        return fileIssueType;
    }

    public void setFileIssueType(String fileIssueType) {
        this.fileIssueType = fileIssueType;
    }

    public String getFileIssueUrl() {
        return fileIssueUrl;
    }

    public void setFileIssueUrl(String fileIssueUrl) {
        this.fileIssueUrl = fileIssueUrl;
    }

    public IssueEntity getIssue() {
        return issue;
    }

    public void setIssue(IssueEntity issue) {
        this.issue = issue;
    }
    
}
