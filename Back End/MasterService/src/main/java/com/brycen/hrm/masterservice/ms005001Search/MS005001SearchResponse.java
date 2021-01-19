package com.brycen.hrm.masterservice.ms005001Search;

import com.brycen.hrm.entity.LanguageEntity;

/**
 * [Description]: Model contain data what want to send to client<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS005001SearchResponse {
    private Long languageID;
    private String languageName;
    private String languageCode;
    private String languageDescription;
    private Long languageCategoryID;
    private String languageCategoryName;

    public MS005001SearchResponse() {
        super();
    }

    public MS005001SearchResponse(LanguageEntity languageEntity) {
        super();
        this.languageID = languageEntity.getLanguageID();
        this.languageName = languageEntity.getLanguageName();
        this.languageCode = languageEntity.getLanguageCode();
        this.languageDescription = languageEntity.getLanguageDescription();
        this.languageCategoryID = languageEntity.getLanguageCategory().getLanguageCategoryID();
        this.languageCategoryName = languageEntity.getLanguageCategory().getLanguageCategoryName();
    }

    public MS005001SearchResponse(Long languageID, String languageName) {
        super();
        this.languageID = languageID;
        this.languageName = languageName;
    }

    public Long getLanguageID() {
        return languageID;
    }

    public void setLanguageID(Long languageID) {
        this.languageID = languageID;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageDescription() {
        return languageDescription;
    }

    public void setLanguageDescription(String languageDescription) {
        this.languageDescription = languageDescription;
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

}
