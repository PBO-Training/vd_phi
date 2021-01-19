package com.brycen.hrm.masterservice.ms004001Search;

import com.brycen.hrm.entity.LevelSkillEntity;

/**
 * [Description]: Search Response for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS004001SearchResponse {

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

    public MS004001SearchResponse() {
        super();
    }

    public MS004001SearchResponse(LevelSkillEntity levelSkillEntity) {
        super();
        this.levelSkillID = levelSkillEntity.getLevelSkillID();
        this.levelSkillName = levelSkillEntity.getLevelSkillName();
        this.levelSkillCode = levelSkillEntity.getLevelSkillCode();
        this.levelSkillDescription = levelSkillEntity.getLevelSkillDescription();
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

}
