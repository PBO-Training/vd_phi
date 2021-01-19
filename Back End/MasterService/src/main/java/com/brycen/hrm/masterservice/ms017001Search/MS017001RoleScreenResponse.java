package com.brycen.hrm.masterservice.ms017001Search;

import com.brycen.hrm.entity.RoleScreenEntity;

public class MS017001RoleScreenResponse {
    
    private Long screenID;
    
    private String screenCode;
    
    private String screenName;
    
    private Long roleID;
    
    private String roleCode;
    
    private boolean access;
    
    private String groupScreenCode;

    public MS017001RoleScreenResponse(RoleScreenEntity roleScreenEntity) {
        super();
        this.roleID = roleScreenEntity.getRole().getRoleID();
        this.roleCode = roleScreenEntity.getRole().getRoleCode();
        this.screenID = roleScreenEntity.getScreen().getScreenID();
        this.screenCode = roleScreenEntity.getScreen().getScreenCode() == null ? null : roleScreenEntity.getScreen().getScreenCode();
        this.screenName = roleScreenEntity.getScreen().getScreenName();
        this.access = roleScreenEntity.isAccess();
        this.groupScreenCode = roleScreenEntity.getScreen().getGroupScreen() == null ? null : roleScreenEntity.getScreen().getGroupScreen().getGroupScreenCode();
    }

    public String getScreenCode() {
        return screenCode;
    }

    public void setScreenCode(String screenCode) {
        this.screenCode = screenCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public Long getScreenID() {
        return screenID;
    }

    public void setScreenID(Long screenID) {
        this.screenID = screenID;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public String getGroupScreenCode() {
        return groupScreenCode;
    }

    public void setGroupScreenCode(String groupScreenCode) {
        this.groupScreenCode = groupScreenCode;
    }
}
