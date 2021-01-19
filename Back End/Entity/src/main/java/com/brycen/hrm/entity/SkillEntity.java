package com.brycen.hrm.entity;

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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * [Description]:Skill Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name = "skill")
public class SkillEntity extends BaseEntity {
    /**
     * Skill Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Long skillID;

    /**
     * Skill Name
     */
    @Column(name = "skill_name", nullable = false)
    private String skillName;

    /**
     * Skill Code
     */
    @Column(name = "skill_code", nullable = false, length = 40)
    private String skillCode;

    /**
     * Skill Description
     */
    @Column(name = "skill_description", length = 2000)
    private String skillDescription;

    /**
     * Type of skill
     */
    @ManyToOne
    @JoinColumn(name = "skill_type_id", nullable = false)
    private SkillTypeEntity skillType;

    /**
     * List Employee Skill
     */
    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    private List<EmployeeSkillEntity> empSkills;

    /**
     * List History Skill
     */
    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    private List<HistorySkillEntity> historySkills;

    public SkillEntity() {

    }

    public SkillEntity(String skillName, String skillCode, String skillDescription, SkillTypeEntity skillType) {
        this.skillName = skillName;
        this.skillCode = skillCode;
        this.skillDescription = skillDescription;
        this.skillType = skillType;
    }

    public Long getSkillID() {
        return skillID;
    }

    public void setSkillID(Long skillID) {
        this.skillID = skillID;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillCode() {
        return skillCode;
    }

    public void setSkillCode(String skillCode) {
        this.skillCode = skillCode;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public List<EmployeeSkillEntity> getEmpSkills() {
        return empSkills;
    }

    public void setEmpSkills(List<EmployeeSkillEntity> empSkills) {
        this.empSkills = empSkills;
    }

    public List<HistorySkillEntity> getHistorySkills() {
        return historySkills;
    }

    public void setHistorySkills(List<HistorySkillEntity> historySkills) {
        this.historySkills = historySkills;
    }

    public SkillTypeEntity getSkillType() {
        return skillType;
    }

    public void setSkillType(SkillTypeEntity skillType) {
        this.skillType = skillType;
    }

}
