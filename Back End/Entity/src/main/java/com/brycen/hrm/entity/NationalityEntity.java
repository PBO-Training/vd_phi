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
 * [Description]:Nationality Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "nationality")
public class NationalityEntity extends BaseEntity {
    /**
     * Nationality Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nationality_id")
    private Long nationalityID;

    /**
     * Nationality Name
     */
    @Column(name = "nationality_name", nullable = false)
    private String nationalityName;

    /**
     * Nationality Code
     */
    @Column(name = "nationality_code", nullable = false, length = 40)
    private String nationalityCode;

    /**
     * List Employee
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "nationality")
    private Set<EmployeeEntity> listEmployee = new HashSet<>();
    
    public NationalityEntity() {

    }

    public NationalityEntity(String nationalityName, String nationalityCode) {
        this.nationalityName = nationalityName;
        this.nationalityCode = nationalityCode;
    }

    public Long getNationalityID() {
        return nationalityID;
    }

    public void setNationalityID(Long nationalityID) {
        this.nationalityID = nationalityID;
    }

    public Set<EmployeeEntity> getListEmployee() {
        return listEmployee;
    }

    public void setListEmployee(Set<EmployeeEntity> listEmployee) {
        this.listEmployee = listEmployee;
    }

    public String getNationalityName() {
        return nationalityName;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }

    public String getNationalityCode() {
        return nationalityCode;
    }

    public void setNationalityCode(String nationalityCode) {
        this.nationalityCode = nationalityCode;
    }
}
