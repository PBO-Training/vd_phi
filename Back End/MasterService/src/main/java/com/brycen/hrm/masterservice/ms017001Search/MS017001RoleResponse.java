package com.brycen.hrm.masterservice.ms017001Search;

import java.util.List;
import java.util.stream.Collectors;

import com.brycen.hrm.entity.RoleEntity;

public class MS017001RoleResponse {

    private String roleCode;
    
    private List<MS017001RoleScreenResponse> listRoleScreen;
    
    public MS017001RoleResponse(RoleEntity roleEntity) {
        super();
        this.roleCode = roleEntity.getRoleCode();
        this.listRoleScreen = roleEntity.getListRoleScreen().stream().map(MS017001RoleScreenResponse::new).collect(Collectors.toList());
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public List<MS017001RoleScreenResponse> getListRoleScreen() {
        return listRoleScreen;
    }

    public void setListRoleScreen(List<MS017001RoleScreenResponse> listRoleScreen) {
        this.listRoleScreen = listRoleScreen;
    }
    
}
