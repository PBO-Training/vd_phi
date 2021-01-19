package com.brycen.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;
import com.brycen.hrm.entity.primaryKey.PrimaryKeySkillProjectRel;

/**
 * [Description]:Table relationship of project and skill project<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "skill_project_rel")
@IdClass(PrimaryKeySkillProjectRel.class)
public class SkillProjectRelEntity {
    /**
     * ID of project
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    /**
     * ID of skill in project
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "skill_project_id", nullable = false)
    private SkillProjectEntity skillProject;

    /**
     * Level skill in project
     */
    @Column(name = "level_skill_project", length = 255)
    private String levelSkillProject;

    public SkillProjectRelEntity() {
        super();
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public SkillProjectEntity getSkillProject() {
        return skillProject;
    }

    public void setSkillProject(SkillProjectEntity skillProject) {
        this.skillProject = skillProject;
    }

    public String getLevelSkillProject() {
        return levelSkillProject;
    }

    public void setLevelSkillProject(String levelSkillProject) {
        this.levelSkillProject = levelSkillProject;
    }
}
