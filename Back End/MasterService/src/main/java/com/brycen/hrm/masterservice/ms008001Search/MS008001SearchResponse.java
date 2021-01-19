package com.brycen.hrm.masterservice.ms008001Search;

import com.brycen.hrm.entity.StatusEmployeeEntity;

/**
 * [Description]: Search Response for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS008001SearchResponse {

    /**
     * Employee Status ID
     */
    private long statusEmployeeID;

    /**
     * Employee Status Code
     */
    private String statusEmployeeCode;

    /**
     * Employee Status Name
     */
    private String statusEmployeeName;

    public MS008001SearchResponse(StatusEmployeeEntity statusEmployeeEntity) {
        super();
        this.statusEmployeeID = statusEmployeeEntity.getStatusEmployeeID();
        this.statusEmployeeCode = statusEmployeeEntity.getStatusEmployeeCode();
        this.statusEmployeeName = statusEmployeeEntity.getStatusEmployeeName();
    }

    public long getStatusEmployeeID() {
        return statusEmployeeID;
    }

    public void setStatusEmployeeID(long statusEmployeeID) {
        this.statusEmployeeID = statusEmployeeID;
    }

    public String getStatusEmployeeName() {
        return statusEmployeeName;
    }

    public void setStatusEmployeeName(String statusEmployeeName) {
        this.statusEmployeeName = statusEmployeeName;
    }

    public String getStatusEmployeeCode() {
        return statusEmployeeCode;
    }

    public void setStatusEmployeeCode(String statusEmployeeCode) {
        this.statusEmployeeCode = statusEmployeeCode;
    }

}
