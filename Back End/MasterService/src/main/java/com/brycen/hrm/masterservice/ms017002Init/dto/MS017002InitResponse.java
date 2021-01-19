package com.brycen.hrm.masterservice.ms017002Init.dto;

/**
 * [Description]:Initialize Response for RoleScreen Detail<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS017002InitResponse {
    /**
     * List Group Screen
     */
    private Object listGroupScreen;

    /**
     * List Action
     */
    private Object listAction;

    /**
     * List Screen
     */
    private Object listScreen;

    public MS017002InitResponse(Object listGroupScreen, Object listScreen, Object listAction) {
        super();
        this.listGroupScreen = listGroupScreen;
        this.listAction = listAction;
        this.listScreen = listScreen;
    }

    public Object getListGroupScreen() {
        return listGroupScreen;
    }

    public void setListGroupScreen(Object listGroupScreen) {
        this.listGroupScreen = listGroupScreen;
    }

    public Object getListAction() {
        return listAction;
    }

    public void setListAction(Object listAction) {
        this.listAction = listAction;
    }

    public Object getListScreen() {
        return listScreen;
    }

    public void setListScreen(Object listScreen) {
        this.listScreen = listScreen;
    }

}
