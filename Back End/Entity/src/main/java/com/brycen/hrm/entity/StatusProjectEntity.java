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
 * [Description]:StatusProject Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "status_project")
public class StatusProjectEntity extends BaseEntity {
    /**
     * Status Project Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_project_id")
    private Long statusProjectID;

    /**
     * Status Project Name
     */
    @Column(name = "status_project_name", nullable = false)
    private String statusProjectName;

    /**
     * Status Project Code
     */
    @Column(name = "status_project_code", length = 40, nullable = false)
    private String statusProjectCode;

    /**
     * Status Project Description
     */
    @Column(name = "status_project_description", length = 2000)
    private String statusProjectDescription;
    
    /**
     * List Project
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "statusProject")
    private Set<ProjectEntity> listProject = new HashSet<>();
    
    public StatusProjectEntity() {

    }

    public StatusProjectEntity(String statusProjectName, String statusProjectCode) {
        this.statusProjectName = statusProjectName;
        this.statusProjectCode = statusProjectCode;
    }

    public Long getStatusProjectID() {
        return statusProjectID;
    }

    public void setStatusProjectID(Long statusProjectID) {
        this.statusProjectID = statusProjectID;
    }

    public String getStatusProjectName() {
        return statusProjectName;
    }

    public void setStatusProjectName(String statusProjectName) {
        this.statusProjectName = statusProjectName;
    }

    public String getStatusProjectCode() {
        return statusProjectCode;
    }

    public void setStatusProjectCode(String statusProjectCode) {
        this.statusProjectCode = statusProjectCode;
    }

    public Set<ProjectEntity> getListProject() {
        return listProject;
    }

    public void setListProject(Set<ProjectEntity> listProject) {
        this.listProject = listProject;
    }

    public String getStatusProjectDescription() {
        return statusProjectDescription;
    }

    public void setStatusProjectDescription(String statusProjectDescription) {
        this.statusProjectDescription = statusProjectDescription;
    }

}
