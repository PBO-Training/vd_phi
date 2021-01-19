package com.brycen.hrm.masterservice.ms001002GetDetail;

import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.RoleEntity;

/**
 * [Description]: Role Response for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public class MS001002GetDetailRoleResponse {

    /**
     * RoleID
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

    public MS001002GetDetailRoleResponse() {
        super();
    }

    public MS001002GetDetailRoleResponse(RoleEntity roleEntity) {
        super();
        this.roleID = roleEntity.getRoleID();
        this.roleName = roleEntity.getRoleName();
        this.roleCode = roleEntity.getRoleCode();
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
