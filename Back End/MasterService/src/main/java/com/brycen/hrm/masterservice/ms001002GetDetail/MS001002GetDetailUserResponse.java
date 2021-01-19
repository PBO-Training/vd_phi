package com.brycen.hrm.masterservice.ms001002GetDetail;

import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.UserEntity;

/**
 * [Description]: User Response for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public class MS001002GetDetailUserResponse {

    /**
     * UserID
     */
    private Long userID;

    /**
     * Username
     */
    private String username;

    /**
     * Password
     */
    private String password;

    /**
     * Responese details for role
     */
    private MS001002GetDetailRoleResponse role;

    /**
     * Responese details for employee
     */
    private MS001002GetDetailEmployeeResponse employee;

    public MS001002GetDetailUserResponse() {
        super();
    }

    public MS001002GetDetailUserResponse(UserEntity userEntity) {
        super();
        this.userID = userEntity.getUserID();
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.role = new MS001002GetDetailRoleResponse(userEntity.getRole());
        this.employee = new MS001002GetDetailEmployeeResponse(userEntity.getEmployee());
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MS001002GetDetailRoleResponse getRole() {
        return role;
    }

    public void setRole(MS001002GetDetailRoleResponse role) {
        this.role = role;
    }

    public MS001002GetDetailEmployeeResponse getEmployee() {
        return employee;
    }

    public void setEmployee(MS001002GetDetailEmployeeResponse employee) {
        this.employee = employee;
    }

}
