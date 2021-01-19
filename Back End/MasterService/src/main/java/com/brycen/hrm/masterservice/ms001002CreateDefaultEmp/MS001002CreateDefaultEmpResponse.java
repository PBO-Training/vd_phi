package com.brycen.hrm.masterservice.ms001002CreateDefaultEmp;

import com.brycen.hrm.entity.EmployeeEntity;

public class MS001002CreateDefaultEmpResponse {
    private Long employeeID;
    private String firstName;
    private String lastName;
    
    public MS001002CreateDefaultEmpResponse(EmployeeEntity employeeEntity) {
        super();
        this.employeeID = employeeEntity.getEmployeeID();
        this.firstName = employeeEntity.getFirstName();
        this.lastName = employeeEntity.getLastName();
    }
    
    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
