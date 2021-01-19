package com.brycen.hrm.masterservice.ms017002GetDetail.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.brycen.hrm.entity.RoleEntity;

public class MS017002GetDetailResponse {
       
    /**
     * Role Code
     */
    private String roleCode;
    
    /**
     * List RoleScreen of Role
     */
    private List<MS017002RoleScreenResponse> listScreen;
    
    public MS017002GetDetailResponse() {
        super();
    }
    public MS017002GetDetailResponse(RoleEntity roleEntity) {
        super();
        this.roleCode = roleEntity.getRoleCode();
        this.listScreen = roleEntity.getListRoleScreen().stream().map(MS017002RoleScreenResponse::new).collect(Collectors.toList());
    }
    public String getRoleCode() {
        return roleCode;
    }
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    public List<MS017002RoleScreenResponse> getListScreen() {
        return listScreen;
    }
    public void setListScreen(List<MS017002RoleScreenResponse> listScreen) {
        this.listScreen = listScreen;
    }
}
