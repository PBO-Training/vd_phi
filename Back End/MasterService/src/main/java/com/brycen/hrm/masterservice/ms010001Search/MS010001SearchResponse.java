package com.brycen.hrm.masterservice.ms010001Search;

import com.brycen.hrm.entity.PositionProjectEntity;

/**
 * [Description]: Object contain data of positionProject to send to client<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS010001SearchResponse {
    /**
     * PositionProject ID
     */
    private long positionProjectID;
    /**
     * PositionProject Code
     */
    private String positionProjectCode;
    /**
     * PositionProject name
     */
    private String positionProjectName;
    /**
     * PositionProject description
     */
    private String positionProjectDescription;

    public MS010001SearchResponse() {
        super();
    }

    public MS010001SearchResponse(PositionProjectEntity positionProjectEntity) {
        super();
        this.positionProjectID = positionProjectEntity.getPositionProjectID();
        this.positionProjectCode = positionProjectEntity.getPositionProjectCode();
        this.positionProjectName = positionProjectEntity.getPositionProjectName();
        this.positionProjectDescription = positionProjectEntity.getPositionProjectDescription();
    }

    public long getPositionProjectID() {
        return positionProjectID;
    }

    public void setPositionProjectID(long positionProjectID) {
        this.positionProjectID = positionProjectID;
    }

    public String getPositionProjectCode() {
        return positionProjectCode;
    }

    public void setPositionProjectCode(String positionProjectCode) {
        this.positionProjectCode = positionProjectCode;
    }

    public String getPositionProjectName() {
        return positionProjectName;
    }

    public void setPositionProjectName(String positionProjectName) {
        this.positionProjectName = positionProjectName;
    }

    public String getPositionProjectDescription() {
        return positionProjectDescription;
    }

    public void setPositionProjectDescription(String positionProjectDescription) {
        this.positionProjectDescription = positionProjectDescription;
    }
}
