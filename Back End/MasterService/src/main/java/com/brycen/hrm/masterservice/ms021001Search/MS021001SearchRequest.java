package com.brycen.hrm.masterservice.ms021001Search;

import com.brycen.hrm.common.base.BaseRequest;

/**
 * [Description]: Modal contain data need to receive from client to search<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS021001SearchRequest extends BaseRequest {
    private String languageCategoryName;
    private String languageCategoryCode;

    public MS021001SearchRequest(String languageCategoryName, String languageCategoryCode) {
        super();
        this.languageCategoryName = languageCategoryName;
        this.languageCategoryCode = languageCategoryCode;
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

}
