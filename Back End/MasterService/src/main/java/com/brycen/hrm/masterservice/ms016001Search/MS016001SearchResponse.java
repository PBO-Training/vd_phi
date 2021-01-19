package com.brycen.hrm.masterservice.ms016001Search;

import com.brycen.hrm.entity.EvaluateProjectEntity;

/**
 * [Description]: Object contain data of evaluateProject to send to client<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS016001SearchResponse {
    /**
     * EvaluateProject ID
     */
    private long evaluateProjectID;
    /**
     * EvaluateProject Code
     */
    private String evaluateProjectCode;
    /**
     * EvaluateProject name
     */
    private String evaluateProjectName;
    /**
     * EvaluateProject description
     */
    private String evaluateProjectDescription;

    public MS016001SearchResponse() {
        super();
    }

    public MS016001SearchResponse(EvaluateProjectEntity evaluateProjectEntity) {
        super();
        this.evaluateProjectID = evaluateProjectEntity.getEvaluateProjectID();
        this.evaluateProjectCode = evaluateProjectEntity.getEvaluateProjectCode();
        this.evaluateProjectName = evaluateProjectEntity.getEvaluateProjectName();
        this.evaluateProjectDescription = evaluateProjectEntity.getEvaluateProjectDescription();
    }

    public long getEvaluateProjectID() {
        return evaluateProjectID;
    }

    public void setEvaluateProjectID(long evaluateProjectID) {
        this.evaluateProjectID = evaluateProjectID;
    }

    public String getEvaluateProjectCode() {
        return evaluateProjectCode;
    }

    public void setEvaluateProjectCode(String evaluateProjectCode) {
        this.evaluateProjectCode = evaluateProjectCode;
    }

    public String getEvaluateProjectName() {
        return evaluateProjectName;
    }

    public void setEvaluateProjectName(String evaluateProjectName) {
        this.evaluateProjectName = evaluateProjectName;
    }

    public String getEvaluateProjectDescription() {
        return evaluateProjectDescription;
    }

    public void setEvaluateProjectDescription(String evaluateProjectDescription) {
        this.evaluateProjectDescription = evaluateProjectDescription;
    }
}
