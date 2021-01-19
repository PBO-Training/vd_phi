package com.brycen.hrm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;
import com.brycen.hrm.entity.primaryKey.PrimaryKeyEmpLanguage;

/**
 * [Description]:EmployeeLanguage Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "employee_language")
@IdClass(PrimaryKeyEmpLanguage.class)
public class EmployeeLanguageEntity extends BaseEntity {

    /**
     * Id of Employee
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    /**
     * Id of Language
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private LanguageEntity language;

    /**
     * Id of Level Language
     */
    // @ManyToOne
    // @JoinColumn(name = "level_language_id", nullable = false)
    // private LevelLanguageEntity levelLanguage;

    /**
     * Expiration Date
     */
    @Column(name = "expiration_date")
    private Date expirationDate;

    /**
     * Language Certificate
     */
    @Column(name = "language_certificate")
    private String languageCertificate;

    public EmployeeLanguageEntity() {

    }

    public EmployeeLanguageEntity(EmployeeEntity employee, LanguageEntity language) {
        this.employee = employee;
        this.language = language;
    }

    public EmployeeLanguageEntity(EmployeeEntity employee, LanguageEntity language, String languageCertificate) {
        this.employee = employee;
        this.language = language;
        this.languageCertificate = languageCertificate;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEntity language) {
        this.language = language;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getLanguageCertificate() {
        return languageCertificate;
    }

    public void setLanguageCertificate(String languageCertificate) {
        this.languageCertificate = languageCertificate;
    }

}
