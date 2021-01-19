package com.brycen.hrm.masterservice.ms001001Search;

import com.brycen.hrm.entity.RoleEntity;

/**
 * [Description]:Sub Search Response For User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS001001RoleResponse {

    /**
     * Role ID
     */
    private Long roleID;

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

    public MS001001RoleResponse() {
        super();
    }

    public MS001001RoleResponse(RoleEntity roleEntity) {
        super();
        this.roleID = roleEntity.getRoleID();
        this.roleName = roleEntity.getRoleName();
        this.roleCode = roleEntity.getRoleCode();
        this.roleValue = roleEntity.getRoleValue();
    }

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleDI(Long roleID) {
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
