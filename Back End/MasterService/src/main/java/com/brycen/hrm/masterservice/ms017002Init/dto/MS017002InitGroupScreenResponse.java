package com.brycen.hrm.masterservice.ms017002Init.dto;

import com.brycen.hrm.entity.GroupScreenEntity;

/**
 * [Description]:<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS017002InitGroupScreenResponse {

    /**
     * Group Screen Code
     */
    private String groupScreenCode;
    
    /**
     * Group Screen Name
     */
    private String groupScreenName;

    public MS017002InitGroupScreenResponse(GroupScreenEntity groupScreenEntity) {
        super();
        this.groupScreenCode = groupScreenEntity.getGroupScreenCode();
        this.groupScreenName = groupScreenEntity.getGroupScreenName();
    }

    public String getGroupScreenCode() {
        return groupScreenCode;
    }

    public void setGroupScreenCode(String groupScreenCode) {
        this.groupScreenCode = groupScreenCode;
    }

    public String getGroupScreenName() {
        return groupScreenName;
    }

    public void setGroupScreenName(String groupScreenName) {
        this.groupScreenName = groupScreenName;
    }
    
    
}
