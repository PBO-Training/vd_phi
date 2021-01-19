package com.brycen.hrm.masterservice.ms999001Init.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.brycen.hrm.entity.DepartmentEntity;

public class MS999001InitDepartmentResponse {
    /**
     * ID of department
     */
    private long departmentID;
    /**
     * Code of department
     */
    private String departmentCode;
    /**
     * Name of department
     */
    private String departmentName;
    /**
     * Description of department
     */
    private String departmentDescription;
    private List<MS999001InitEmployeeResponse> employee;

    public MS999001InitDepartmentResponse() {
        super();
    }

    public MS999001InitDepartmentResponse(DepartmentEntity departmentEntity) {
        super();
        this.departmentID = departmentEntity.getDepartmentID();
        this.departmentCode = departmentEntity.getDepartmentCode();
        this.departmentName = departmentEntity.getDepartmentName();
        this.departmentDescription = departmentEntity.getDepartmentDescription();
        this.employee = departmentEntity.getListEmployee().stream().map(MS999001InitEmployeeResponse::new).collect(Collectors.toList());
    }

    public long getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(long departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    public List<MS999001InitEmployeeResponse> getEmployee() {
        return employee;
    }

    public void setEmployee(List<MS999001InitEmployeeResponse> employee) {
        this.employee = employee;
    }
}
