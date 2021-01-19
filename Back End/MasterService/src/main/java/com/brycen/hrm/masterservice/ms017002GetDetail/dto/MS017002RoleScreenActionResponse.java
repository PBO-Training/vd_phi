package com.brycen.hrm.masterservice.ms017002GetDetail.dto;

import com.brycen.hrm.entity.RoleScreenActionEntity;

public class MS017002RoleScreenActionResponse {
    /**
     * Action Code
     */
    private String actionCode;

    /**
     * Action Name
     */
    private String actionName;

    private boolean access;

    public MS017002RoleScreenActionResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MS017002RoleScreenActionResponse(RoleScreenActionEntity roleScreenActionEntity) {
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
