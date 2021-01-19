package com.brycen.hrm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * [Description]: Company Entity [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "company")
public class CompanyEntity {

    /**
     * ID of Company
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int companyID;

    /**
     * Flag delete
     */
    @Column(name = "is_delete", columnDefinition = "boolean default false")
    private boolean isDelete;

    /**
     * Create by User
     */
    @CreatedBy
    @Column(name = "create_by", nullable = false)
    private int createBy;

    /**
     * Last modified by User
     */
    @LastModifiedBy
    @Column(name = "update_by", nullable = false)
    private int updateBy;

    /**
     * Date create
     */
    @CreatedDate
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    /**
     * Last Modified Date
     */
    @LastModifiedDate
    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    /**
     * Name of Company
     */
    @Column(name = "company_name", nullable = false, length = 255)
    private String companyName;

    /**
     * Code of Company
     */
    @Column(name = "company_code", unique = true, nullable = false, length = 40)
    private String companyCode;
    
    public CompanyEntity() {
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int lastModifiedBy) {
        this.updateBy = lastModifiedBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date lastModifiedDate) {
        this.updateDate = lastModifiedDate;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
