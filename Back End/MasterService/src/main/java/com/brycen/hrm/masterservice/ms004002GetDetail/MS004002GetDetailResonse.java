package com.brycen.hrm.masterservice.ms004002GetDetail;

import com.brycen.hrm.entity.LevelSkillEntity;

/**
 * [Description]: Get Details Response for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS004002GetDetailResonse {

    /**
     * Skill Level ID
     */
    private long levelSkillID;

    /**
     * Skill Level Name
     */
    private String levelSkillName;

    /**
     * Skill Level Code
     */
    private String levelSkillCode;

    /**
     * Skill Level Description
     */
    private String levelSkillDescription;
    
    /**
     * Skill Level Value
     */
    private int levelSkillValue;

    public MS004002GetDetailResonse() {
        super();
    }

    public MS004002GetDetailResonse(LevelSkillEntity levelSkillEntity) {
        super();
        this.levelSkillID = levelSkillEntity.getLevelSkillID();
        this.levelSkillName = levelSkillEntity.getLevelSkillName();
        this.levelSkillCode = levelSkillEntity.getLevelSkillCode();
        this.levelSkillDescription = levelSkillEntity.getLevelSkillDescription();
        this.levelSkillValue = levelSkillEntity.getLevelSkillValue();
    }

    public long getLevelSkillID() {
        return levelSkillID;
    }

    public void setLevelSkillID(long levelSkillID) {
        this.levelSkillID = levelSkillID;
    }

    public String getLevelSkillName() {
        return levelSkillName;
    }

    public void setLevelSkillName(String levelSkillName) {
        this.levelSkillName = levelSkillName;
    }

    public String getLevelSkillCode() {
        return levelSkillCode;
    }

    public void setLevelSkillCode(String levelSkillCode) {
        this.levelSkillCode = levelSkillCode;
    }

    public String getLevelSkillDescription() {
        return levelSkillDescription;
    }

    public void setLevelSkillDescription(String levelSkillDescription) {
        this.levelSkillDescription = levelSkillDescription;
    }
    
    public int getLevelSkillValue() {
        return levelSkillValue;
    }
    
    public void setLevelSkillValue(int levelSkillValue) {
        this.levelSkillValue = levelSkillValue;
    }

}
