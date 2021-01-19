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
 * [Description]:VacationOption Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "vacation_option")
public class VacationOptionEntity extends BaseEntity {
    /**
     * Vacation Option Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_option_id")
    private Long vacationOptionID;

    /**
     * Vacation Option Name
     */
    @Column(name = "vacation_option_name", nullable = false)
    private String vacationOptionName;

    /**
     * Vacation Option Time
     */
    @Column(name = "is_time", nullable = false)    
    private int isTime;
    
    /**
     * List Vacation
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vacationOption")
    private Set<VacationEntity> listVacation = new HashSet<>();
    
    public VacationOptionEntity() {

    }

    public VacationOptionEntity(String vacationOptionName, int isTime) {
        this.vacationOptionName = vacationOptionName;
        this.isTime = isTime;
    }

    public Long getVacationOptionID() {
        return vacationOptionID;
    }

    public void setVacationOptionID(Long vacationOptionID) {
        this.vacationOptionID = vacationOptionID;
    }

    public String getVacationOptionName() {
        return vacationOptionName;
    }

    public void setVacationOptionName(String vacationOptionName) {
        this.vacationOptionName = vacationOptionName;
    }

    public int getIsTime() {
        return isTime;
    }

    public void setIsTime(int isTime) {
        this.isTime = isTime;
    }

    public Set<VacationEntity> getListVacation() {
        return listVacation;
    }

    public void setListVacation(Set<VacationEntity> listVacation) {
        this.listVacation = listVacation;
    }
    
    
}
