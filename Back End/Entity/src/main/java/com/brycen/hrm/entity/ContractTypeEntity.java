package com.brycen.hrm.entity;

import java.util.List;

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
 * [Description]:ContractType Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "contract_type")
public class ContractTypeEntity extends BaseEntity {
    /**
     * ID of Contract Type
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_type_id")
    private Long contractTypeID;

    /**
     * Name of Contract Type
     */
    @Column(name = "contract_type_name", nullable = false)
    private String contractTypeName;

    /**
     * Code or name short of Contract Type
     */
    @Column(name = "contract_type_code", nullable = false, length = 40)
    private String contractTypeCode;

    /**
     * Description of Contract Type
     */
    @Column(name = "contract_type_description", length = 2000)
    private String contractTypeDescription;

    /**
     * List Contract
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contractType")
    private List<ContractEntity> listContract;

    public ContractTypeEntity() {

    }

    public ContractTypeEntity(String contractTypeName, String contractTypeCode, String contractTypeDescription) {
        this.contractTypeName = contractTypeName;
        this.contractTypeCode = contractTypeCode;
        this.contractTypeDescription = contractTypeDescription;
    }

    public Long getContractTypeID() {
        return contractTypeID;
    }

    public void setContractTypeID(Long contractTypeID) {
        this.contractTypeID = contractTypeID;
    }

    public String getContractTypeName() {
        return contractTypeName;
    }

    public void setContractTypeName(String contractTypeName) {
        this.contractTypeName = contractTypeName;
    }

    public String getContractTypeCode() {
        return contractTypeCode;
    }

    public void setContractTypeCode(String contractTypeCode) {
        this.contractTypeCode = contractTypeCode;
    }

    public String getContractTypeDescription() {
        return contractTypeDescription;
    }

    public void setContractTypeDescription(String contractTypeDescription) {
        this.contractTypeDescription = contractTypeDescription;
    }

    public List<ContractEntity> getListContract() {
        return listContract;
    }

    public void setListContract(List<ContractEntity> listContract) {
        this.listContract = listContract;
    }

}
