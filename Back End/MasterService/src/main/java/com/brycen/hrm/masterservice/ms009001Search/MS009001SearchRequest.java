package com.brycen.hrm.masterservice.ms009001Search;

import com.brycen.hrm.common.base.BaseRequest;

public class MS009001SearchRequest extends BaseRequest {
    /**
     * Customer code
     */
    private String customerCode;
    /**
     * Customer name
     */
    private String customerName;

    public MS009001SearchRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MS009001SearchRequest(String customerCode, String customerName) {
        super();
        this.customerCode = customerCode;
        this.customerName = customerName;
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
}
