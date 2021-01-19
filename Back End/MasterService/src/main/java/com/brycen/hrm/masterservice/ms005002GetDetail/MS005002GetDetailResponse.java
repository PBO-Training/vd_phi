package com.brycen.hrm.masterservice.ms005002GetDetail;

import com.brycen.hrm.entity.LanguageEntity;

public class MS005002GetDetailResponse {
    private long languageID;
    private String languageName;
    private String languageCode;
    private String languageDescription;
    private long languageCategoryID;
    private String languageCategoryName;

    public MS005002GetDetailResponse() {
        super();
    }

    public MS005002GetDetailResponse(LanguageEntity languageEntity) {
        super();
        this.languageID = languageEntity.getLanguageID();
        this.languageName = languageEntity.getLanguageName();
        this.languageCode = languageEntity.getLanguageCode();
        this.languageDescription = languageEntity.getLanguageDescription();
        this.languageCategoryID = languageEntity.getLanguageCategory().getLanguageCategoryID();
        this.languageCategoryName = languageEntity.getLanguageCategory().getLanguageCategoryName();
    }

    public long getLanguageID() {
        return languageID;
    }

    public void setLanguageID(long languageID) {
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

    public long getLanguageCategoryID() {
        return languageCategoryID;
    }

    public void setLanguageCategoryID(long languageCategoryID) {
        this.languageCategoryID = languageCategoryID;
    }

    public String getLanguageCategoryName() {
        return languageCategoryName;
    }

    public void setLanguageCategoryName(String languageCategoryName) {
        this.languageCategoryName = languageCategoryName;
    }

}
