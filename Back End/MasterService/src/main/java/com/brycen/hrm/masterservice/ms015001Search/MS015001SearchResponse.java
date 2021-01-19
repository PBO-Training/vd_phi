package com.brycen.hrm.masterservice.ms015001Search;

import com.brycen.hrm.entity.ContractTypeEntity;

public class MS015001SearchResponse {
    /**
     * Name of contract type
     */
    private String contractTypeName;
    /**
     * Code of contract type
     */
    private String contractTypeCode;
    /**
     * Description of contract type
     */
    private String contractTypeDescription;

    public MS015001SearchResponse() {
        super();
    }

    public MS015001SearchResponse(ContractTypeEntity contractTypeEntity) {
        super();
        this.contractTypeName = contractTypeEntity.getContractTypeName();
        this.contractTypeCode = contractTypeEntity.getContractTypeCode();
        this.contractTypeDescription = contractTypeEntity.getContractTypeDescription();
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
}
