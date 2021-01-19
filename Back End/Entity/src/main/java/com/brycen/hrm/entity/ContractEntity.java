package com.brycen.hrm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:ContractType Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "contract")
public class ContractEntity extends BaseEntity {
    /**
     * ID of Contract
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long contractID;
    /**
     * Name of Contract
     */
    @Column(name = "contract_start_date")
    private Date contractStartDate;
    /**
     * Name of Contract
     */
    @Column(name = "contract_end_date")
    private Date contractEndDate;
    /**
     * Contract Type
     */
    @ManyToOne
    @JoinColumn(name = "contract_type_id", nullable = false)
    private ContractTypeEntity contractType;
    /**
     * Position Employee
     */
    @ManyToOne
    @JoinColumn(name = "position_employee_id", nullable = false)
    private PositionEmployeeEntity positionEmployee;
    /**
     * Attachments of Contract
     */
    @Column(name = "contract_attachments", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String contractAttachments;
    /**
     * Position Employee
     */
    // @ManyToOne
    // @JoinColumn(name = "employee_id", nullable = false)
    // private EmployeeEntity employee;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    public Long getContractID() {
        return contractID;
    }

    public void setContractID(Long contractID) {
        this.contractID = contractID;
    }

    public Date getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public ContractTypeEntity getContractType() {
        return contractType;
    }

    public void setContractType(ContractTypeEntity contractType) {
        this.contractType = contractType;
    }

    public PositionEmployeeEntity getPositionEmployee() {
        return positionEmployee;
    }

    public void setPositionEmployee(PositionEmployeeEntity positionEmployee) {
        this.positionEmployee = positionEmployee;
    }

    public String getContractAttachments() {
        return contractAttachments;
    }

    public void setContractAttachments(String contractAttachments) {
        this.contractAttachments = contractAttachments;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

}
