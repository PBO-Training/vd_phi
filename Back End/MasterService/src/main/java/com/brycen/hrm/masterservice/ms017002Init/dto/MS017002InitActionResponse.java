package com.brycen.hrm.masterservice.ms017002Init.dto;

import com.brycen.hrm.entity.ActionEntity;

/**
 * [Description]:<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS017002InitActionResponse {

    /**
     * Action Code
     */
    private String actionCode;
    
    /**
     * Action Name
     */
    private String actionName;

    public MS017002InitActionResponse(ActionEntity actionEnity) {
        super();
        this.actionCode = actionEnity.getActionCode();
        this.actionName = actionEnity.getActionName();
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
    
}
