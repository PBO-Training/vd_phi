package com.brycen.hrm.masterservice.ms012002GetDetail;

import com.brycen.hrm.entity.VacationTypeEntity;

public class MS012002GetDetailResponse {
    private long vacationTypeID;
    private String vacationTypeName;
    private String vacationTypeCode;
    private String vacationTypeDescription;

    public MS012002GetDetailResponse() {
        super();
    }

    public MS012002GetDetailResponse(VacationTypeEntity vacationTypeEntity) {
        super();
        this.vacationTypeID = vacationTypeEntity.getVacationTypeID();
        this.vacationTypeName = vacationTypeEntity.getVacationTypeName();
        this.vacationTypeCode = vacationTypeEntity.getVacationTypeCode();
        this.vacationTypeDescription = vacationTypeEntity.getVacationTypeDescription();
    }

    /**
     * @return the vacationTypeID
     */
    public long getVacationTypeID() {
        return vacationTypeID;
    }

    /**
     * @param vacationTypeID the vacationTypeID to set
     */
    public void setVacationTypeID(long vacationTypeID) {
        this.vacationTypeID = vacationTypeID;
    }

    /**
     * @return the vacationTypeName
     */
    public String getVacationTypeName() {
        return vacationTypeName;
    }

    /**
     * @param vacationTypeName the vacationTypeName to set
     */
    public void setVacationTypeName(String vacationTypeName) {
        this.vacationTypeName = vacationTypeName;
    }

    /**
     * @return the vacationTypeCode
     */
    public String getVacationTypeCode() {
        return vacationTypeCode;
    }

    /**
     * @param vacationTypeCode the vacationTypeCode to set
     */
    public void setVacationTypeCode(String vacationTypeCode) {
        this.vacationTypeCode = vacationTypeCode;
    }

    /**
     * @return the vacationTypeDescription
     */
    public String getVacationTypeDescription() {
        return vacationTypeDescription;
    }

    /**
     * @param vacationTypeDescription the vacationTypeDescription to set
     */
    public void setVacationTypeDescription(String vacationTypeDescription) {
        this.vacationTypeDescription = vacationTypeDescription;
    }

}
