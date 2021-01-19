package com.brycen.hrm.masterservice.ms018001Search;

import com.brycen.hrm.entity.RoleEntity;

/**
 * [Description]: Search Response for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS018001SearchResponse {

    /**
     * Role ID
     */
    private Long roleID;

    /**
     * Role name
     */
    private String roleName;

    /**
     * Role code
     */
    private String roleCode;

    /**
     * Role value
     */
    private int roleValue;

    public MS018001SearchResponse(RoleEntity roleEntity) {
        super();
        this.roleID = roleEntity.getRoleID();
        this.roleCode = roleEntity.getRoleCode();
        this.roleName = roleEntity.getRoleName();
        this.roleValue = roleEntity.getRoleValue();
    }

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
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
