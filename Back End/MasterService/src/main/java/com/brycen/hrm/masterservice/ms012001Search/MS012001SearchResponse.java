package com.brycen.hrm.masterservice.ms012001Search;

import com.brycen.hrm.entity.VacationTypeEntity;

/**
 * [Description]: Create constructure of List<Object> in ContentResponse. This is data who client want to see<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS012001SearchResponse {
    private long vacationTypeID;
    private String vacationTypeName;
    private String vacationTypeCode;
    private String vacationTypeDescription;

    public MS012001SearchResponse() {
        super();
    }

    public MS012001SearchResponse(VacationTypeEntity vacationTypeEntity) {
        super();
        this.vacationTypeID = vacationTypeEntity.getVacationTypeID();
        this.vacationTypeName = vacationTypeEntity.getVacationTypeName();
        this.vacationTypeCode = vacationTypeEntity.getVacationTypeCode();
        this.vacationTypeDescription = vacationTypeEntity.getVacationTypeDescription();
    }

    public long getVacationTypeID() {
        return vacationTypeID;
    }

    public void setVacationTypeID(long vacationTypeID) {
        this.vacationTypeID = vacationTypeID;
    }

    public String getVacationTypeName() {
        return vacationTypeName;
    }

    public void setVacationTypeName(String vacationTypeName) {
        this.vacationTypeName = vacationTypeName;
    }

    public String getVacationTypeCode() {
        return vacationTypeCode;
    }

    public void setVacationTypeCode(String vacationTypeCode) {
        this.vacationTypeCode = vacationTypeCode;
    }

    public String getVacationTypeDescription() {
        return vacationTypeDescription;
    }

    public void setVacationTypeDescription(String vacationTypeDescription) {
        this.vacationTypeDescription = vacationTypeDescription;
    }
}
