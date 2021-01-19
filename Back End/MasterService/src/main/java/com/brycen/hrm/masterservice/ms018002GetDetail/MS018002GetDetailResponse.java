package com.brycen.hrm.masterservice.ms018002GetDetail;

import com.brycen.hrm.entity.RoleEntity;

/**
 * [Description]:<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS018002GetDetailResponse {

    /**
     * Role ID
     */
    private long roleID;

    /**
     * Role Name
     */
    private String roleName;

    /**
     * Role Code
     */
    private String roleCode;

    /**
     * Role Value
     */
    private int roleValue;

    public MS018002GetDetailResponse() {
        super();
    }

    public MS018002GetDetailResponse(RoleEntity roleEntity) {
        super();
        this.roleID = roleEntity.getRoleID();
        this.roleName = roleEntity.getRoleName();
        this.roleCode = roleEntity.getRoleCode();
        this.roleValue = roleEntity.getRoleValue();
    }

    public long getRoleID() {
        return roleID;
    }

    public void setRoleID(long roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public int getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(int roleValue) {
        this.roleValue = roleValue;
    }

}
