package com.brycen.hrm.common.base;

/**
 * [Description]:Error Model [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class ErrorResponse {
    /**
     * Code of Error
     */
    private String code;

    /**
     * Item error
     */
    private String itemName;

    public ErrorResponse(String code, String itemName) {
        this.code = code;
        this.itemName = itemName;
    }

    public ErrorResponse() {
        this.code = null;
        this.itemName = null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
