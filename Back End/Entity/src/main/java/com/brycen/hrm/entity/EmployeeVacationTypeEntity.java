package com.brycen.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brycen.hrm.entity.primaryKey.PrimaryKeyEmpVacationType;

/**
 * [Description]:EmployeeVacationType Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "emp_vacation_type")
@IdClass(PrimaryKeyEmpVacationType.class)
public class EmployeeVacationTypeEntity {
    /**
     * Employee ID
     */
    @Id
    @ManyToOne 
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;
    
    /**
     * Vacation Type Id
     */
    @Id
    @ManyToOne 
    @JoinColumn(name = "vacation_type_id", nullable = false)
    private VacationTypeEntity vacationType;
    
    /**
     * Vacation Number
     */
    @Column(name = "vacation_number")
    private int vacationNumber;
    
    public EmployeeVacationTypeEntity()
    {
        
    }
    
    public EmployeeVacationTypeEntity(EmployeeEntity employee, VacationTypeEntity vacationType, int vacationNumber)
    {
        this.employee = employee;
        this.vacationType = vacationType;
        this.vacationNumber = vacationNumber;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public VacationTypeEntity getVacationType() {
        return vacationType;
    }

    public void setVacationType(VacationTypeEntity vacationType) {
        this.vacationType = vacationType;
    }

    public int getVacationNumber() {
        return vacationNumber;
    }

    public void setVacationNumber(int vacationNumber) {
        this.vacationNumber = vacationNumber;
    }
    
    
}
