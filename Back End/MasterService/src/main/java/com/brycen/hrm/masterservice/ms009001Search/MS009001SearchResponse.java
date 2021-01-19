package com.brycen.hrm.masterservice.ms009001Search;

import com.brycen.hrm.entity.CustomerEntity;

/**
 * [Description]: Object contain data of customer to send to client<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS009001SearchResponse {
    /**
     * Customer ID
     */
    private long customerID;
    /**
     * Customer Code
     */
    private String customerCode;
    /**
     * Customer name
     */
    private String customerName;
    /**
     * Customer description
     */
    private String customerDescription;

    public MS009001SearchResponse(CustomerEntity customerEntity) {
        super();
        this.customerID = customerEntity.getCustomerID();
        this.customerCode = customerEntity.getCustomerCode();
        this.customerName = customerEntity.getCustomerName();
        this.customerDescription = customerEntity.getCustomerDescription();
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerDescription() {
        return customerDescription;
    }

    public void setCustomerDescription(String customerDescription) {
        this.customerDescription = customerDescription;
    }
}
