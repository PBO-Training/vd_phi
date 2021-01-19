package com.brycen.hrm.entity;

import java.util.List;

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
 * [Description]:Type of skill Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "skill_type")
public class SkillTypeEntity extends BaseEntity {
    /**
     * ID of type skill
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_type_id")
    private long skillTypeID;
    /**
     * Name of type skill
     */
    @Column(name = "skill_type_name")
    private String skillTypeName;
    /**
     * Code of type skill
     */
    @Column(name = "skill_type_code")
    private String skillTypeCode;
    /**
     * Description of type skill
     */
    @Column(name = "skill_type_description")
    private String skillTypeDescription;
    /**
     * List Skill
     */
 
    @OneToMany(mappedBy = "skillType", fetch = FetchType.LAZY)
    private List<SkillEntity> listSkill;

    public SkillTypeEntity() {
    }

    public long getSkillTypeID() {
        return skillTypeID;
    }

    public void setSkillTypeID(long skillTypeID) {
        this.skillTypeID = skillTypeID;
    }

    public String getSkillTypeName() {
        return skillTypeName;
    }

    public void setSkillTypeName(String skillTypeName) {
        this.skillTypeName = skillTypeName;
    }

    public String getSkillTypeCode() {
        return skillTypeCode;
    }

    public void setSkillTypeCode(String skillTypeCode) {
        this.skillTypeCode = skillTypeCode;
    }

    public String getSkillTypeDescription() {
        return skillTypeDescription;
    }

    public void setSkillTypeDescription(String skillTypeDescription) {
        this.skillTypeDescription = skillTypeDescription;
    }

    public List<SkillEntity> getListSkill() {
        return listSkill;
    }

    public void setListSkill(List<SkillEntity> listSkill) {
        this.listSkill = listSkill;
    }
}
