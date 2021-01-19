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
 * [Description]:LevelProject Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "level_Project")
public class LevelProjectEntity extends BaseEntity {
    /**
     * Level Project Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_project_id")
    private Long levelProjectID;

    /**
     * Level Project Name
     */
    @Column(name = "level_project_name", nullable = false)
    private String levelProjectName;

    /**
     * Level Project Code
     */
    @Column(name = "level_project_code", length = 40, nullable = false)
    private String levelProjectCode;
    
    /**
     * Level Project Description
     */
    @Column(name = "level_project_description", length = 2000)
    private String levelProjectDescription;
    
    /**
     * List Project
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "levelProject")
    private Set<ProjectEntity> listProject = new HashSet<>();

    public LevelProjectEntity() {

    }

    public LevelProjectEntity(String levelProjectName, String levelProjectCode, String levelProjectDescription) {
        this.levelProjectName = levelProjectName;
        this.levelProjectCode = levelProjectCode;
        this.levelProjectDescription = levelProjectDescription;
    }

    public Long getLevelProjectID() {
        return levelProjectID;
    }

    public void setLevelProjectID(Long levelProjectID) {
        this.levelProjectID = levelProjectID;
    }

    public String getLevelProjectName() {
        return levelProjectName;
    }

    public void setLevelProjectName(String levelProjectName) {
        this.levelProjectName = levelProjectName;
    }

    public String getLevelProjectCode() {
        return levelProjectCode;
    }

    public void setLevelProjectCode(String levelProjectCode) {
        this.levelProjectCode = levelProjectCode;
    }

    public String getLevelProjectDescription() {
        return levelProjectDescription;
    }

    public void setLevelProjectDescription(String levelProjectDescription) {
        this.levelProjectDescription = levelProjectDescription;
    }

    public Set<ProjectEntity> getListProject() {
        return listProject;
    }

    public void setListProject(Set<ProjectEntity> listProject) {
        this.listProject = listProject;
    }
}
