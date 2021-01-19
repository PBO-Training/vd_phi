package com.brycen.hrm.masterservice.ms000000Login;

import java.util.List;
import java.util.stream.Collectors;

import com.brycen.hrm.entity.RoleEntity;

/**
 * [Description]:Custom Role Response<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS000000RoleResponse {
    /**
     * Role Value
     */
    private int roleValue;
       
    /**
     * Role Code
     */
    private String roleCode;
    
    /**
     * List RoleScreen of Role
     */
    private List<MS000000RoleScreenResponse> listScreen;
    
    public MS000000RoleResponse() {
        super();
        // TODO Auto-generated constructor stub
    }
    public MS000000RoleResponse(RoleEntity roleEntity) {
        super();
        this.roleValue = roleEntity.getRoleValue();
        this.roleCode = roleEntity.getRoleCode();
        this.listScreen = roleEntity.getListRoleScreen().stream().map(MS000000RoleScreenResponse::new).collect(Collectors.toList());
    }
    public int getRoleValue() {
        return roleValue;
    }
    public void setRoleValue(int roleValue) {
        this.roleValue = roleValue;
    }
    public String getRoleCode() {
        return roleCode;
    }
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    public List<MS000000RoleScreenResponse> getListScreen() {
        return listScreen;
    }
    public void setListScreen(List<MS000000RoleScreenResponse> listScreen) {
        this.listScreen = listScreen;
    }

    
    
}
