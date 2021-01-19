package com.brycen.hrm.masterservice.ms007001Search;

import com.brycen.hrm.entity.PositionEmployeeEntity;

/**
 * [Description]: Create constructure of List<Object> in ContentResponse. This is data who client want to see<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS007001SearchResponse {
    private long positionEmployeeID;
    private String positionEmployeeName;
    private String positionEmployeeCode;
    private String positionEmployeeDescription;

    public MS007001SearchResponse() {
        super();
    }

    public MS007001SearchResponse(PositionEmployeeEntity positionEmployeeEntity) {
        super();
        this.positionEmployeeID = positionEmployeeEntity.getPositionEmployeeID();
        this.positionEmployeeName = positionEmployeeEntity.getPositionEmployeeName();
        this.positionEmployeeCode = positionEmployeeEntity.getPositionEmployeeCode();
        this.positionEmployeeDescription = positionEmployeeEntity.getPositionEmployeeDescription();
    }

    public long getPositionEmployeeID() {
        return positionEmployeeID;
    }

    public void setPositionEmployeeID(long positionEmployeeID) {
        this.positionEmployeeID = positionEmployeeID;
    }

    public String getPositionEmployeeName() {
        return positionEmployeeName;
    }

    public void setPositionEmployeeName(String positionEmployeeName) {
        this.positionEmployeeName = positionEmployeeName;
    }

    public String getPositionEmployeeCode() {
        return positionEmployeeCode;
    }

    public void setPositionEmployeeCode(String positionEmployeeCode) {
        this.positionEmployeeCode = positionEmployeeCode;
    }

    public String getPositionEmployeeDescription() {
        return positionEmployeeDescription;
    }

    public void setPositionEmployeeDescription(String positionEmployeeDescription) {
        this.positionEmployeeDescription = positionEmployeeDescription;
    }
}
