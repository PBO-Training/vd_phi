package com.brycen.hrm.masterservice.ms006002GetDetail;

import com.brycen.hrm.entity.LevelLanguageEntity;

public class MS006002GetDetailResponse {
    private Long levelLanguageID;
    private String levelLanguageName;
    private String levelLanguageCode;
    private String levelLanguageDescription;
    private int levelLanguageValue;

    public MS006002GetDetailResponse() {
        super();
    }

    public MS006002GetDetailResponse(LevelLanguageEntity levelLanguageEntity) {
        super();
        this.levelLanguageID = levelLanguageEntity.getLevelLanguageID();
        this.levelLanguageName = levelLanguageEntity.getLevelLanguageName();
        this.levelLanguageCode = levelLanguageEntity.getLevelLanguageCode();
        this.levelLanguageDescription = levelLanguageEntity.getLevelLanguageDescription();
        this.levelLanguageValue = levelLanguageEntity.getLevelLanguageValue();
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

	public int getLevelLanguageValue() {
		return levelLanguageValue;
	}

	public void setLevelLanguageValue(int levelLanguageValue) {
		this.levelLanguageValue = levelLanguageValue;
	}

}
