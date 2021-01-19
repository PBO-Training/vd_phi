package com.brycen.hrm.masterservice.ms008002GetDetail;

import com.brycen.hrm.entity.StatusEmployeeEntity;

/**
 * [Description]: Get Details Response for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS008002GetDetailResponse {

    /**
     * Employee Status ID
     */
    private Long statusEmployeeID;

    /**
     * Employee Status Name
     */
    private String statusEmployeeName;

    /**
     * Employee Status Code
     */
    private String statusEmployeeCode;

    public MS008002GetDetailResponse() {
        super();
    }

    public MS008002GetDetailResponse(StatusEmployeeEntity statusEmployeeEntity) {
        super();
        this.statusEmployeeID = statusEmployeeEntity.getStatusEmployeeID();
        this.statusEmployeeName = statusEmployeeEntity.getStatusEmployeeName();
        this.statusEmployeeCode = statusEmployeeEntity.getStatusEmployeeCode();
    }

    public Long getStatusEmployeeID() {
        return statusEmployeeID;
    }

    public void setStatusEmployeeID(Long statusEmployeeID) {
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
