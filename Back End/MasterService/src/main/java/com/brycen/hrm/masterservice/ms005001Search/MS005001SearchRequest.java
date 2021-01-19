package com.brycen.hrm.masterservice.ms005001Search;

import com.brycen.hrm.common.base.BaseRequest;

/**
 * [Description]: Modal contain data need to receive from client to search<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS005001SearchRequest extends BaseRequest {
    private String languageName;
    private String languageCode;

    public MS005001SearchRequest(String languageName, String languageCode) {
        super();
        this.languageName = languageName;
        this.languageCode = languageCode;
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

}
