package com.brycen.hrm.masterservice.ms001001Init;

/**
 * [Description]: Initialize Response for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS001001InitResponse {

    /**
     * List Department
     */
    private Object listDepartment;

    /**
     * List Role
     */
    private Object listRole;

    public MS001001InitResponse() {
    }

    public MS001001InitResponse(Object listDepartment, Object listRole) {
        this.listDepartment = listDepartment;
        this.listRole = listRole;
    }

    public Object getListDepartment() {
        return listDepartment;
    }

    public void setListDepartment(Object listDepartment) {
        this.listDepartment = listDepartment;
    }

    public Object getListRole() {
        return listRole;
    }

    public void setListRole(Object listRole) {
        this.listRole = listRole;
    }

}
