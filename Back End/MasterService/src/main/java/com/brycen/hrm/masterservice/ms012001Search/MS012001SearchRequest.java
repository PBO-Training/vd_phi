package com.brycen.hrm.masterservice.ms012001Search;

import com.brycen.hrm.common.base.BaseRequest;

/**
 * [Description]: Modal contain data need to receive from client to search<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS012001SearchRequest extends BaseRequest {
    private String vacationTypeName;
    private String vacationTypeCode;

    public MS012001SearchRequest(String vacationTypeName, String vacationTypeCode) {
        super();
        this.vacationTypeName = vacationTypeName;
        this.vacationTypeCode = vacationTypeCode;
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
}
