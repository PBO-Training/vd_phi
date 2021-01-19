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
 * [Description]:Document Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "document")
public class DocumentEntity extends BaseEntity {
    /**
     * Document Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentID;

    /**
     * document Name
     */
    @Column(name = "document_name", nullable = false)
    private String documentName;

    /**
     * document Description
     */
    @Column(name = "document_description", length = 2000)
    private String documentDescription;

    /**
     * document Url
     */
    @Column(name = "document_url", length = 2000, nullable = false)
    private String documentUrl;

    /**
     * Project Id
     */
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    public DocumentEntity() {
    }

    public DocumentEntity(String documentName, String documentDescription, String documentUrl, ProjectEntity project) {
        this.documentName = documentName;
        this.documentDescription = documentDescription;
        this.documentUrl = documentUrl;
        this.project = project;
    }

    public Long getDocumentID() {
        return documentID;
    }

    public void setDocumentID(Long documentID) {
        this.documentID = documentID;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentDescription() {
        return documentDescription;
    }

    public void setDocumentDescription(String documentDescription) {
        this.documentDescription = documentDescription;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

}
