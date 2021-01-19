package com.brycen.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;
import com.brycen.hrm.entity.primaryKey.PrimaryKeyEmpSkill;

/**
 * [Description]:EmployeeSkill Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "employee_skill")
@IdClass(PrimaryKeyEmpSkill.class)
public class EmployeeSkillEntity extends BaseEntity {

    /**
     * Employee ID
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    /**
     * Skill Id
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private SkillEntity skill;

    /**
     * Level Skill Id
     */
    @ManyToOne
    @JoinColumn(name = "level_skill_id", nullable = false)
    private LevelSkillEntity levelSkill;

    /**
     * Exp of employee about this skill
     */
    @Column(name = "skill_experience", nullable = false)
    private Double skillExperience;

    public EmployeeSkillEntity() {

    }

    public EmployeeSkillEntity(EmployeeEntity employee, SkillEntity skill, LevelSkillEntity levelSkill) {
        this.employee = employee;
        this.skill = skill;
        this.levelSkill = levelSkill;
    }

    public EmployeeSkillEntity(EmployeeEntity employee, SkillEntity skill, LevelSkillEntity levelSkill, Double skillExperience) {
        super();
        this.employee = employee;
        this.skill = skill;
        this.levelSkill = levelSkill;
        this.skillExperience = skillExperience;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public SkillEntity getSkill() {
        return skill;
    }

    public void setSkill(SkillEntity skill) {
        this.skill = skill;
    }

    public LevelSkillEntity getLevelSkill() {
        return levelSkill;
    }

    public void setLevelSkill(LevelSkillEntity levelSkill) {
        this.levelSkill = levelSkill;
    }

    public Double getSkillExperience() {
        return skillExperience;
    }

    public void setSkillExperience(Double skillExperience) {
        this.skillExperience = skillExperience;
    }

}
