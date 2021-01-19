package com.brycen.hrm.masterservice.ms021001Search;

import com.brycen.hrm.entity.LanguageCategoryEntity;

/**
 * [Description]: Model contain data what want to send to client<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS021001SearchResponse {
    private Long languageCategoryID;
    private String languageCategoryName;
    private String languageCategoryCode;
    private String languageCategoryDescription;

    public MS021001SearchResponse() {
        super();
    }

    public MS021001SearchResponse(LanguageCategoryEntity languageCategoryEntity) {
        super();
        this.languageCategoryID = languageCategoryEntity.getLanguageCategoryID();
        this.languageCategoryName = languageCategoryEntity.getLanguageCategoryName();
        this.languageCategoryCode = languageCategoryEntity.getLanguageCategoryCode();
        this.languageCategoryDescription = languageCategoryEntity.getLanguageCategoryDescription();
    }

    public MS021001SearchResponse(Long languageCategoryID, String languageCategoryName) {
        super();
        this.languageCategoryID = languageCategoryID;
        this.languageCategoryName = languageCategoryName;
    }

    public Long getLanguageCategoryID() {
        return languageCategoryID;
    }

    public void setLanguageCategoryID(Long languageCategoryID) {
        this.languageCategoryID = languageCategoryID;
    }

    public String getLanguageCategoryName() {
        return languageCategoryName;
    }

    public void setLanguageCategoryName(String languageCategoryName) {
        this.languageCategoryName = languageCategoryName;
    }

    public String getLanguageCategoryCode() {
        return languageCategoryCode;
    }

    public void setLanguageCategoryCode(String languageCategoryCode) {
        this.languageCategoryCode = languageCategoryCode;
    }

    public String getLanguageCategoryDescription() {
        return languageCategoryDescription;
    }

    public void setLanguageCategoryDescription(String languageCategoryDescription) {
        this.languageCategoryDescription = languageCategoryDescription;
    }

}
