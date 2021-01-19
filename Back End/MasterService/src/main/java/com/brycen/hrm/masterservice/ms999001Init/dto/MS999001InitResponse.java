package com.brycen.hrm.masterservice.ms999001Init.dto;

import java.util.List;

public class MS999001InitResponse {
    /**
     * Total employee in company (include employee free and employee in department)
     */
    private int totalEmployee;
    /**
     * Employees join company in month
     */
    private MS999001InitEmployeeJoinInMonth empJoinInMonth;
    /**
     * List department in company (include employee in department)
     */
    private List<MS999001InitDepartmentResponse> department;

    public MS999001InitResponse() {
        super();
    }

    public MS999001InitResponse(int totalEmployee, MS999001InitEmployeeJoinInMonth empJoinInMonth, List<MS999001InitDepartmentResponse> department) {
        super();
        this.totalEmployee = totalEmployee;
        this.empJoinInMonth = empJoinInMonth;
        this.department = department;
    }

    public int getTotalEmployee() {
        return totalEmployee;
    }

    public void setTotalEmployee(int totalEmployee) {
        this.totalEmployee = totalEmployee;
    }

    public MS999001InitEmployeeJoinInMonth getEmpJoinInMonth() {
        return empJoinInMonth;
    }

    public void setEmpJoinInMonth(MS999001InitEmployeeJoinInMonth empJoinInMonth) {
        this.empJoinInMonth = empJoinInMonth;
    }

    public List<MS999001InitDepartmentResponse> getDepartment() {
        return department;
    }

    public void setDepartment(List<MS999001InitDepartmentResponse> department) {
        this.department = department;
    }
}
