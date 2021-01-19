package com.brycen.hrm.masterservice.ms022001Search;

import com.brycen.hrm.entity.SkillProjectEntity;

public class MS022001SearchResponse {
    /**
     * ID of skill project
     */
    private long skillProjectID;
    /**
     * Name of skill project
     */
    private String skillProjectName;

    public MS022001SearchResponse() {
        super();
    }

    public MS022001SearchResponse(SkillProjectEntity skillProjectEntity) {
        super();
        this.skillProjectID = skillProjectEntity.getSkillProjectID();
        this.skillProjectName = skillProjectEntity.getSkillProjectName();
    }

    public long getSkillProjectID() {
        return skillProjectID;
    }

    public void setSkillProjectID(long skillProjectID) {
        this.skillProjectID = skillProjectID;
    }

    public String getSkillProjectName() {
        return skillProjectName;
    }

    public void setSkillProjectName(String skillProjectName) {
        this.skillProjectName = skillProjectName;
    }
}
