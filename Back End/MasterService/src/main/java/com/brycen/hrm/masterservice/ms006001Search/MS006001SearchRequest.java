package com.brycen.hrm.masterservice.ms006001Search;

import com.brycen.hrm.common.base.BaseRequest;

public class MS006001SearchRequest extends BaseRequest {
    /**
     * Name of level language
     */
    private String levelLanguageName;
    /**
     * Code of level language
     */
    private String levelLanguageCode;

    public MS006001SearchRequest() {
        super();
    }

    public MS006001SearchRequest(String levelLanguageName, String levelLanguageCode) {
        super();
        this.levelLanguageName = levelLanguageName;
        this.levelLanguageCode = levelLanguageCode;
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
}
