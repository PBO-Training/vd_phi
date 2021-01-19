package com.brycen.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

@Entity
@Table(name = "vacation_status")
public class VacationStatusEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_status_id")
    private Long vacationStatusID;

    /**
     * Vacation Status Name
     */
    @Column(name = "vacation_status_name", nullable = false)
    private String vacationStatusName;

    /**
     * Vacation Status Code
     */
    @Column(name = "vacation_status_code", nullable = false, length = 40)
    private String vacationStatusCode;

    /**
     * Vacation Status Description
     */
    @Column(name = "vacation_status_description", length = 2000)
    private String vacationStatusDescription;

    public VacationStatusEntity() {
    }

    public Long getVacationStatusID() {
        return vacationStatusID;
    }

    public void setVacationStatusID(Long vacationStatusID) {
        this.vacationStatusID = vacationStatusID;
    }

    public String getVacationStatusName() {
        return vacationStatusName;
    }

    public void setVacationStatusName(String vacationStatusName) {
        this.vacationStatusName = vacationStatusName;
    }

    public String getVacationStatusCode() {
        return vacationStatusCode;
    }

    public void setVacationStatusCode(String vacationStatusCode) {
        this.vacationStatusCode = vacationStatusCode;
    }

    public String getVacationStatusDescription() {
        return vacationStatusDescription;
    }

    public void setVacationStatusDescription(String vacationStatusDescription) {
        this.vacationStatusDescription = vacationStatusDescription;
    }

}
