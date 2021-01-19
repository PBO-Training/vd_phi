
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
 * [Description]:Customer Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {

    /**
     * ID of customer
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerID;

    /**
     * Customer Name
     */
    @Column(name = "customer_name", nullable = false)
    private String customerName;

    /**
     * Customer Code
     */
    @Column(name = "customer_code", length = 40, nullable = false)
    private String customerCode;

    /**
     * Customer Description
     */
    @Column(name = "customer_description", length = 2000)
    private String customerDescription;
    
    /**
     * List Project
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<ProjectEntity> listProject = new HashSet<>();
    
    public CustomerEntity() {

    }

    public CustomerEntity(String customerName, String customerCode, String customerDescription) {
        this.customerName = customerName;
        this.customerCode = customerCode;
        this.customerDescription = customerDescription;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerDescription() {
        return customerDescription;
    }

    public void setCustomerDescription(String customerDescription) {
        this.customerDescription = customerDescription;
    }

    public Set<ProjectEntity> getListProject() {
        return listProject;
    }

    public void setListProject(Set<ProjectEntity> listProject) {
        this.listProject = listProject;
    }
}
