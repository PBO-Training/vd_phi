package com.brycen.hrm.masterservice.ms017001Search;

public class MS017001SearchResponse {
    private Object listGroupScreen;
    private Object listRole;
    
    public MS017001SearchResponse() {
        super();
    }

    public MS017001SearchResponse(Object listGroupScreen, Object listRole) {
        super();
        this.listGroupScreen = listGroupScreen;
        this.listRole = listRole;
    }
    
    public Object getListGroupScreen() {
        return listGroupScreen;
    }
    
    public void setListGroupScreen(Object listGroupScreen) {
        this.listGroupScreen = listGroupScreen;
    }
    
    public Object getListRole() {
        return listRole;
    }
    
    public void setListRole(Object listRole) {
        this.listRole = listRole;
    }
    
}
