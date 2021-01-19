package com.brycen.hrm.masterservice.ms011001Search;

import com.brycen.hrm.entity.LevelProjectEntity;

/**
 * [Description]: Object contain data of levelProject to send to client<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS011001SearchResponse {
    /**
     * LevelProject ID
     */
    private long levelProjectID;
    /**
     * LevelProject Code
     */
    private String levelProjectCode;
    /**
     * LevelProject name
     */
    private String levelProjectName;
    /**
     * LevelProject description
     */
    private String levelProjectDescription;

    public MS011001SearchResponse() {
        super();
    }

    public MS011001SearchResponse(LevelProjectEntity levelProjectEntity) {
        super();
        this.levelProjectID = levelProjectEntity.getLevelProjectID();
        this.levelProjectCode = levelProjectEntity.getLevelProjectCode();
        this.levelProjectName = levelProjectEntity.getLevelProjectName();
        this.levelProjectDescription = levelProjectEntity.getLevelProjectDescription();
    }

    public long getLevelProjectID() {
        return levelProjectID;
    }

    public void setLevelProjectID(long levelProjectID) {
        this.levelProjectID = levelProjectID;
    }

    public String getLevelProjectCode() {
        return levelProjectCode;
    }

    public void setLevelProjectCode(String levelProjectCode) {
        this.levelProjectCode = levelProjectCode;
    }

    public String getLevelProjectName() {
        return levelProjectName;
    }

    public void setLevelProjectName(String levelProjectName) {
        this.levelProjectName = levelProjectName;
    }

    public String getLevelProjectDescription() {
        return levelProjectDescription;
    }

    public void setLevelProjectDescription(String levelProjectDescription) {
        this.levelProjectDescription = levelProjectDescription;
    }
}
