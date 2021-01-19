package com.brycen.hrm.masterservice.ms017002GetDetail.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.brycen.hrm.entity.ActionEntity;
import com.brycen.hrm.entity.ActionGroupActionEntity;
import com.brycen.hrm.entity.RoleScreenEntity;

public class MS017002RoleScreenResponse {
    /**
     * Access of Screen: true/false
     */
    private boolean access;

    /**
     * Screen Code
     */
    private String screenCode;

    /**
     * Group Screen Code
     */
    private String groupScreenCode;
       
    /**
     * List action of screen
     */
    private List<MS017002RoleScreenActionResponse> listAction;

    public MS017002RoleScreenResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MS017002RoleScreenResponse(RoleScreenEntity roleScreenEntity) {
        super();
        this.access = roleScreenEntity.isAccess();
        this.screenCode = roleScreenEntity.getScreen().getScreenCode();
        this.groupScreenCode = roleScreenEntity.getScreen().getGroupScreen() == null ? null : roleScreenEntity.getScreen().getGroupScreen().getGroupScreenCode();
        this.listAction = roleScreenEntity.getScreen().getListAction()
                .stream()
                .filter(u -> u.getRole().getRoleID() == roleScreenEntity.getRole().getRoleID() && isExistGroupAction(roleScreenEntity, u.getAction()))
                .map(MS017002RoleScreenActionResponse::new).collect(Collectors.toList());
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public List<MS017002RoleScreenActionResponse> getListAction() {
        return listAction;
    }

    public void setListAction(List<MS017002RoleScreenActionResponse> listAction) {
        this.listAction = listAction;
    }
    
    public String getScreenCode() {
        return screenCode;
    }

    public void setScreenCode(String screenCode) {
        this.screenCode = screenCode;
    }

    public String getGroupScreenCode() {
        return groupScreenCode;
    }

    public void setGroupScreenCode(String groupScreenCode) {
        this.groupScreenCode = groupScreenCode;
    }

    private boolean isExistGroupAction(RoleScreenEntity roleScreenEntity, ActionEntity action) {        
        for (ActionGroupActionEntity actionGroupActionEntity : action.getListGroupAction()) {
            if(roleScreenEntity.getScreen().getGroupAction().getGroupActionCode().equals(actionGroupActionEntity.getGroupAction().getGroupActionCode()))
                return true;
        }
        return false;
    }
}
