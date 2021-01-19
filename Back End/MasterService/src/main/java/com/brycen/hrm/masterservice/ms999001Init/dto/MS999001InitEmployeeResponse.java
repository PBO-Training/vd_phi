package com.brycen.hrm.masterservice.ms999001Init.dto;

import com.brycen.hrm.entity.EmployeeEntity;

public class MS999001InitEmployeeResponse {
    /**
     * Code of employee
     */
    private String employeeCode;

    public MS999001InitEmployeeResponse() {
        super();
    }

    public MS999001InitEmployeeResponse(EmployeeEntity employeeEntity) {
        super();
        if (employeeEntity.getIsDelete() == false) {
            this.employeeCode = employeeEntity.getEmployeeCode();
        }
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }
}
