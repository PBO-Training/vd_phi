package com.brycen.hrm.masterservice.ms006001Search;

import com.brycen.hrm.entity.LevelLanguageEntity;

public class MS006001SearchResponse {
    /**
     * ID of level language
     */
    private Long levelLanguageID;
    /**
     * Name of level language
     */
    private String levelLanguageName;
    /**
     * Code of level language
     */
    private String levelLanguageCode;
    /**
     * Description of level language
     */
    private String levelLanguageDescription;

    public MS006001SearchResponse() {
        super();
    }

    public MS006001SearchResponse(LevelLanguageEntity levelLanguageEntity) {
        super();
        this.levelLanguageID = levelLanguageEntity.getLevelLanguageID();
        this.levelLanguageName = levelLanguageEntity.getLevelLanguageName();
        this.levelLanguageCode = levelLanguageEntity.getLevelLanguageCode();
        this.levelLanguageDescription = levelLanguageEntity.getLevelLanguageDescription();
    }

    public Long getLevelLanguageID() {
        return levelLanguageID;
    }

    public void setLevelLanguageID(Long levelLanguageID) {
        this.levelLanguageID = levelLanguageID;
    }

    public String getLevelLanguageName() {
        return levelLanguageName;
    }

    public void setLevelLanguageName(String levelLanguageName) {
        this.levelLanguageName = levelLanguageName;
    }

    public String getLevelLanguageCode() {
        return levelLanguageCode;
    }

    public void setLevelLanguageCode(String levelLanguageCode) {
        this.levelLanguageCode = levelLanguageCode;
    }

    public String getLevelLanguageDescription() {
        return levelLanguageDescription;
    }

    public void setLevelLanguageDescription(String levelLanguageDescription) {
        this.levelLanguageDescription = levelLanguageDescription;
    }
}
