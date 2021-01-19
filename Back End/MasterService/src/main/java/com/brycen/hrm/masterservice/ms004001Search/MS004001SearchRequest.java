package com.brycen.hrm.masterservice.ms004001Search;

import com.brycen.hrm.common.base.BaseRequest;

/**
 * [Description]: Search Request for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS004001SearchRequest extends BaseRequest {

    /**
     * Skill Level Name
     */
    private String levelSkillName;

    /**
     * Skill Level Code
     */
    private String levelSkillCode;

    public MS004001SearchRequest(String levelSkillName, String levelSkillCode) {
        super();
        this.levelSkillName = levelSkillName;
        this.levelSkillCode = levelSkillCode;
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

}
