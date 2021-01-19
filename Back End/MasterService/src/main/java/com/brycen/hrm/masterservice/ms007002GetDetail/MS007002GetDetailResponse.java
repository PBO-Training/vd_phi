package com.brycen.hrm.masterservice.ms007002GetDetail;

import com.brycen.hrm.entity.PositionEmployeeEntity;

public class MS007002GetDetailResponse {
    private long positionEmployeeID;
    private String positionEmployeeName;
    private String positionEmployeeCode;
    private String positionEmployeeDescription;

    public MS007002GetDetailResponse() {
        super();
    }

    public MS007002GetDetailResponse(PositionEmployeeEntity positionEmployeeEntity) {
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
