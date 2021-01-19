package com.brycen.hrm.masterservice.ms009002GetDetail;

import com.brycen.hrm.entity.CustomerEntity;

public class MS009002GetDetailResponse {
    /**
     * Customer ID
     */
    private long customerID;
    /**
     * Customer name
     */
    private String customerName;
    /**
     * Customer code
     */
    private String customerCode;
    /**
     * Customer description
     */
    private String customerDescription;

    public MS009002GetDetailResponse() {
        super();
    }

    public MS009002GetDetailResponse(CustomerEntity customerEntity) {
        super();
        this.customerID = customerEntity.getCustomerID();
        this.customerName = customerEntity.getCustomerName();
        this.customerCode = customerEntity.getCustomerCode();
        this.customerDescription = customerEntity.getCustomerDescription();
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
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
}
