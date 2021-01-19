package com.brycen.hrm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:Skills of project<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "skill_project")
public class SkillProjectEntity extends BaseEntity {
    /**
     * ID of skill in project
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_project_id")
    private Long skillProjectID;
    /**
     * Code of skill in project
     */
    @Column(name = "skill_project_code", length = 40)
    private String skillProjectCode;
    /**
     * Name of skill in project
     */
    @Column(name = "skill_project_name", length = 255)
    private String skillProjectName;
    /**
     * Description of skill in project
     */
    @Column(name = "skill_project_description", length = 2000)
    private String skillProjectDescription;
    /**
     * List Skill project relationship
     */
    @OneToMany(mappedBy = "skillProject", cascade = CascadeType.ALL)
    private List<SkillProjectRelEntity> skillProjectRel;

    public SkillProjectEntity() {
        super();
    }

    public Long getSkillProjectID() {
        return skillProjectID;
    }

    public void setSkillProjectID(Long skillProjectID) {
        this.skillProjectID = skillProjectID;
    }

    public String getSkillProjectCode() {
        return skillProjectCode;
    }

    public void setSkillProjectCode(String skillProjectCode) {
        this.skillProjectCode = skillProjectCode;
    }

    public String getSkillProjectName() {
        return skillProjectName;
    }

    public void setSkillProjectName(String skillProjectName) {
        this.skillProjectName = skillProjectName;
    }

    public String getSkillProjectDescription() {
        return skillProjectDescription;
    }

    public void setSkillProjectDescription(String skillProjectDescription) {
        this.skillProjectDescription = skillProjectDescription;
    }

    public List<SkillProjectRelEntity> getSkillProjectRel() {
        return skillProjectRel;
    }

    public void setSkillProjectRel(List<SkillProjectRelEntity> skillProjectRel) {
        this.skillProjectRel = skillProjectRel;
    }
}
