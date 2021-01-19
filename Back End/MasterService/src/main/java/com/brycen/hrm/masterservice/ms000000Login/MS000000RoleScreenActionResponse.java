package com.brycen.hrm.masterservice.ms000000Login;

import com.brycen.hrm.entity.RoleScreenActionEntity;

/**
 * [Description]:Custom RoleScreenAction Response<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS000000RoleScreenActionResponse {
    /**
     * Action Code
     */
    private String actionCode;

    /**
     * Action Name
     */
    private String actionName;

    private boolean access;

    public MS000000RoleScreenActionResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MS000000RoleScreenActionResponse(RoleScreenActionEntity roleScreenActionEntity) {
        super();
        this.actionCode = roleScreenActionEntity.getAction().getActionCode();
        this.actionName = roleScreenActionEntity.getAction().getActionName();
        this.access = roleScreenActionEntity.getAccess();
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

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

}
