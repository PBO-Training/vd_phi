package com.brycen.hrm.masterservice.ms017002Update;

import java.util.List;

public class MS017002UpdateRequest {
    /**
     * Role Code
     */
    private String roleCode;
    
    /**
     * Screen Code
     */
    private String screenCode;
    
    /**
     * List Action
     */
    private List<MS017002UpdateActionRequest> listAction;
    
    public String getRoleCode() {
        return roleCode;
    }
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    public String getScreenCode() {
        return screenCode;
    }
    public void setScreenCode(String screenCode) {
        this.screenCode = screenCode;
    }
    public List<MS017002UpdateActionRequest> getListAction() {
        return listAction;
    }
    public void setListAction(List<MS017002UpdateActionRequest> listAction) {
        this.listAction = listAction;
    }
    
    
}
