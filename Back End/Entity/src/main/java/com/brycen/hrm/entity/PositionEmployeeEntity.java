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
 * [Description]:PositionEmployee Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "position_employee")
public class PositionEmployeeEntity extends BaseEntity {

    /**
     * Possition Employee Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_employee_id")
    private Long positionEmployeeID;

    /**
     * Position Employee Name
     */
    @Column(name = "position_employee_name", nullable = false)
    private String positionEmployeeName;

    /**
     * Position Employee Code
     */
    @Column(name = "position_employee_code", nullable = false, length = 40)
    private String positionEmployeeCode;
    
    /**
     * Position Employee Description
     */
    @Column(name = "position_employee_description", length = 2000)
    private String positionEmployeeDescription;

    /**
     * List Employee
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "positionEmployee")
    private Set<EmployeeEntity> listEmployee = new HashSet<>();
    
    public PositionEmployeeEntity() {

    }

    public PositionEmployeeEntity(String positionEmployeeName, String positionEmployeeCode, String positionEmployeeDescription) {
        this.positionEmployeeName = positionEmployeeName;
        this.positionEmployeeCode = positionEmployeeCode;
        this.positionEmployeeDescription = positionEmployeeDescription;
    }

    public Long getPositionEmployeeID() {
        return positionEmployeeID;
    }

    public void setPositionEmployeeID(Long positionEmployeeID) {
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
    
    public Set<EmployeeEntity> getListEmployee() {
        return listEmployee;
    }

    public void setListEmployee(Set<EmployeeEntity> listEmployee) {
        this.listEmployee = listEmployee;
    }

    public String getPositionEmployeeDescription() {
        return positionEmployeeDescription;
    }

    public void setPositionEmployeeDescription(String positionEmployeeDescription) {
        this.positionEmployeeDescription = positionEmployeeDescription;
    }
}
