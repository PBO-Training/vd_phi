package com.brycen.hrm.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
 * [Description]:VacationType Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "vacation_type")
public class VacationTypeEntity extends BaseEntity{
    /**
     * ID of Vacation Type
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_type_id")
    private Long vacationTypeID;

    /**
     * Vacation Type Name
     */
    @Column(name = "vacation_type_name", nullable = false)
    private String vacationTypeName;

    /**
     * Vacation Type Code
     */
    @Column(name = "vacation_type_code", nullable = false, length = 40)
    private String vacationTypeCode;
    
    /**
     * Vacation Type Description
     */
    @Column(name = "vacation_type_description", length = 2000)
    private String vacationTypeDescription;
    
    /**
     * Is reduced leave vacation
     */
    @Column(name = "is_reduced", nullable = true)
    private Boolean isReduced;

    /**
     * List Vacation
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vacationType")
    private Set<VacationEntity> listVacation = new HashSet<>();
    
    /**
     * List vacation Type
     */
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeVacationTypeEntity> litsEmpVacationType;
    
    public VacationTypeEntity() {

    }

    public VacationTypeEntity(String vacationTypeName, String vacationTypeCode, String vacationTypeDescription) {
        this.vacationTypeName = vacationTypeName;
        this.vacationTypeCode = vacationTypeCode;
        this.vacationTypeDescription = vacationTypeDescription;
    }

    public Long getVacationTypeID() {
        return vacationTypeID;
    }

    public void setVacationTypeID(Long vacationTypeID) {
        this.vacationTypeID = vacationTypeID;
    }

    public String getVacationTypeName() {
        return vacationTypeName;
    }

    public void setVacationTypeName(String vacationTypeName) {
        this.vacationTypeName = vacationTypeName;
    }

    public String getVacationTypeCode() {
        return vacationTypeCode;
    }

    public void setVacationTypeCode(String vacationTypeCode) {
        this.vacationTypeCode = vacationTypeCode;
    }

    public String getVacationTypeDescription() {
        return vacationTypeDescription;
    }

    public void setVacationTypeDescription(String vacationTypeDescription) {
        this.vacationTypeDescription = vacationTypeDescription;
    }
    
    public Boolean getIsReduced() {
        return isReduced;
    }

    public void setIsReduced(Boolean isReduced) {
        this.isReduced = isReduced;
    }

    public Set<VacationEntity> getListVacation() {
        return listVacation;
    }

    public void setListVacation(Set<VacationEntity> listVacation) {
        this.listVacation = listVacation;
    }

    public List<EmployeeVacationTypeEntity> getLitsEmpVacationType() {
        return litsEmpVacationType;
    }

    public void setLitsEmpVacationType(List<EmployeeVacationTypeEntity> litsEmpVacationType) {
        this.litsEmpVacationType = litsEmpVacationType;
    }
    
}
