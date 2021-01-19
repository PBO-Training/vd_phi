package com.brycen.hrm.masterservice.ms018001Search;

import com.brycen.hrm.common.base.BaseRequest;

/**
 * [Description]: Search Request for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS018001SearchRequest extends BaseRequest {

    /**
     * Role name
     */
    private String roleName;

    /**
     * Role code
     */
    private String roleCode;

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

}
