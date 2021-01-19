package com.brycen.hrm.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:StatusEmployee Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "status_employee")
public class StatusEmployeeEntity extends BaseEntity {
    /**
     * Status Employee Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_employee_id")
    private Long statusEmployeeID;

    /**
     * Status Employee Name
     */
    @Column(name = "status_employee_name", nullable = false)
    private String statusEmployeeName;

    /**
     * Status Employee Code
     */
    @Column(name = "status_employee_code", nullable = false, length = 40)
    private String statusEmployeeCode;

    /**
     * List Employee
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "statusEmployee")
    private Set<EmployeeEntity> listEmployee = new HashSet<>();

    public StatusEmployeeEntity() {

    }

    public StatusEmployeeEntity(String statusEmployeeName, String statusEmployeeCode) {
        this.statusEmployeeName = statusEmployeeName;
        this.statusEmployeeCode = statusEmployeeCode;
    }

    public Long getStatusEmployeeID() {
        return statusEmployeeID;
    }

    public void setStatusEmployeeID(Long statusEmployeeID) {
        this.statusEmployeeID = statusEmployeeID;
    }

    public String getStatusEmployeeName() {
        return statusEmployeeName;
    }

    public void setStatusEmployeeName(String statusEmployeeName) {
        this.statusEmployeeName = statusEmployeeName;
    }

    public String getStatusEmployeeCode() {
        return statusEmployeeCode;
    }

    public void setStatusEmployeeCode(String statusEmployeeCode) {
        this.statusEmployeeCode = statusEmployeeCode;
    }
    
    public Set<EmployeeEntity> getListEmployee() {
        return listEmployee;
    }

    public void setListEmployee(Set<EmployeeEntity> listEmployee) {
        this.listEmployee = listEmployee;
    }
}
