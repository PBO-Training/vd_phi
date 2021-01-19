package com.brycen.hrm.masterservice.ms017002Init.dto;

import com.brycen.hrm.entity.ScreenEntity;

/**
 * [Description]:<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS017002InitScreenResponse {

    /**
     * Screen Code
     */
    private String screenCode;

    /**
     * Screen Name
     */
    private String screenName;

    /**
     * Group Screen of Screen
     */
    private String groupScreenCode;

    /**
     * Group Screen of Screen
     */
    private String screenUrl;

    public MS017002InitScreenResponse(ScreenEntity screenEntity) {
        super();
        this.screenCode = screenEntity.getScreenCode();
        this.screenName = screenEntity.getScreenName();
        this.groupScreenCode = screenEntity.getGroupScreen().getGroupScreenCode();
        this.screenUrl = screenEntity.getScreenUrl();
    }

    public String getScreenCode() {
        return screenCode;
    }

    public void setScreenCode(String screenCode) {
        this.screenCode = screenCode;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getGroupScreenCode() {
        return groupScreenCode;
    }

    public void setGroupScreenCode(String groupScreenCode) {
        this.groupScreenCode = groupScreenCode;
    }

    public String getScreenUrl() {
        return screenUrl;
    }

    public void setScreenUrl(String screenUrl) {
        this.screenUrl = screenUrl;
    }
}
