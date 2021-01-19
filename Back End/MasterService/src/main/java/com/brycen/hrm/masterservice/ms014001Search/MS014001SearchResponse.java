package com.brycen.hrm.masterservice.ms014001Search;

import com.brycen.hrm.entity.StatusProjectEntity;

/**
 * [Description]: Object contain data of statusProject to send to client<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS014001SearchResponse {
    /**
     * StatusProject ID
     */
    private long statusProjectID;
    /**
     * StatusProject Code
     */
    private String statusProjectCode;
    /**
     * StatusProject name
     */
    private String statusProjectName;
    /**
     * StatusProject description
     */
    private String statusProjectDescription;

    public MS014001SearchResponse() {
        super();
    }

    public MS014001SearchResponse(StatusProjectEntity statusProjectEntity) {
        super();
        this.statusProjectID = statusProjectEntity.getStatusProjectID();
        this.statusProjectCode = statusProjectEntity.getStatusProjectCode();
        this.statusProjectName = statusProjectEntity.getStatusProjectName();
        this.statusProjectDescription = statusProjectEntity.getStatusProjectDescription();
    }

    public long getStatusProjectID() {
        return statusProjectID;
    }

    public void setStatusProjectID(long statusProjectID) {
        this.statusProjectID = statusProjectID;
    }

    public String getStatusProjectCode() {
        return statusProjectCode;
    }

    public void setStatusProjectCode(String statusProjectCode) {
        this.statusProjectCode = statusProjectCode;
    }

    public String getStatusProjectName() {
        return statusProjectName;
    }

    public void setStatusProjectName(String statusProjectName) {
        this.statusProjectName = statusProjectName;
    }

    public String getStatusProjectDescription() {
        return statusProjectDescription;
    }

    public void setStatusProjectDescription(String statusProjectDescription) {
        this.statusProjectDescription = statusProjectDescription;
    }
}
