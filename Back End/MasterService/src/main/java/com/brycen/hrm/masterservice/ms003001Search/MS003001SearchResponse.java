package com.brycen.hrm.masterservice.ms003001Search;

import com.brycen.hrm.entity.SkillEntity;

/**
 * [Description]: Create constructure of List<Object> in ContentResponse. This is data who client want to see<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS003001SearchResponse {
    private long skillID;
    private String skillName;
    private String skillCode;
    private String skillDescription;
    private long skillTypeID;
    private String skillTypeName;

    public MS003001SearchResponse() {
        super();
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

    public MS003001SearchResponse(SkillEntity skillEntity) {
        if (skillEntity.getIsDelete() == false) {
            this.skillID = skillEntity.getSkillID();
            this.skillName = skillEntity.getSkillName();
            this.skillCode = skillEntity.getSkillCode();
            this.skillDescription = skillEntity.getSkillDescription();
            if (skillEntity.getSkillType() != null && skillEntity.getSkillType().getIsDelete() == false) {
                this.skillTypeID = skillEntity.getSkillType().getSkillTypeID();
                this.skillTypeName = skillEntity.getSkillType().getSkillTypeName();
            } else {
                this.skillTypeID = 0;
            }
        }
    }

    public MS003001SearchResponse(long skillID, String skillName, String skillCode, String skillDescription, long skillTypeID) {
        super();
        this.skillID = skillID;
        this.skillName = skillName;
        this.skillCode = skillCode;
        this.skillDescription = skillDescription;
        this.skillTypeID = skillTypeID;
    }

    public MS003001SearchResponse(long skillID, String skillName) {
        super();
        this.skillID = skillID;
        this.skillName = skillName;
    }

    public long getSkillID() {
        return skillID;
    }

    public void setSkillID(long skillID) {
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
}
