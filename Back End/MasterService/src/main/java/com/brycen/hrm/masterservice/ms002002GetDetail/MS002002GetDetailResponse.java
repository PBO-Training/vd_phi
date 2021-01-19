package com.brycen.hrm.masterservice.ms002002GetDetail;

import com.brycen.hrm.entity.DepartmentEntity;

public class MS002002GetDetailResponse {
    private long departmentID;
    private String departmentName;
    private String departmentCode;
    private String departmentDescription;

    public MS002002GetDetailResponse() {
        super();
    }

    public MS002002GetDetailResponse(DepartmentEntity departmentEntity) {
        super();
        this.departmentID = departmentEntity.getDepartmentID();
        this.departmentName = departmentEntity.getDepartmentName();
        this.departmentCode = departmentEntity.getDepartmentCode();
        this.departmentDescription = departmentEntity.getDepartmentDescription();
    }

    public long getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(long departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }
}
