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
 * [Description]:EvaluateProject Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "evaluate_project")
public class EvaluateProjectEntity extends BaseEntity {

    /**
     * Evaluate Project Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluate_project_id")
    private Long evaluateProjectID;

    /**
     * Evaluate Project Name
     */
    @Column(name = "evaluate_project_name", nullable = false)
    private String evaluateProjectName;

    /**
     * Evaluate Project Code
     */
    @Column(name = "evaluate_project_code", nullable = false, length = 40)
    private String evaluateProjectCode;
    
    /**
     * Evaluate Project Description
     */
    @Column(name = "evaluate_project_description")
    private String evaluateProjectDescription;
    
    /**
     * List Project
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "evaluateProject")
    private Set<ProjectEntity> listProject = new HashSet<>();

    public EvaluateProjectEntity() {

    }

    public EvaluateProjectEntity(String evaluateProjectName, String evaluateProjectCode, String evaluateProjectDescription) {
        this.evaluateProjectName = evaluateProjectName;
        this.evaluateProjectCode = evaluateProjectCode;
        this.evaluateProjectDescription = evaluateProjectDescription;
    }

    public Long getEvaluateProjectID() {
        return evaluateProjectID;
    }

    public void setEvaluateProjectID(Long evaluateProjectID) {
        this.evaluateProjectID = evaluateProjectID;
    }

    public String getEvaluateProjectName() {
        return evaluateProjectName;
    }

    public void setEvaluateProjectName(String evaluateProjectName) {
        this.evaluateProjectName = evaluateProjectName;
    }

    public String getEvaluateProjectCode() {
        return evaluateProjectCode;
    }

    public void setEvaluateProjectCode(String evaluateProjectCode) {
        this.evaluateProjectCode = evaluateProjectCode;
    }

    public String getEvaluateProjectDescription() {
        return evaluateProjectDescription;
    }

    public void setEvaluateProjectDescription(String evaluateProjectDescription) {
        this.evaluateProjectDescription = evaluateProjectDescription;
    }

    public Set<ProjectEntity> getListProject() {
        return listProject;
    }

    public void setListProject(Set<ProjectEntity> listProject) {
        this.listProject = listProject;
    }
    
}
