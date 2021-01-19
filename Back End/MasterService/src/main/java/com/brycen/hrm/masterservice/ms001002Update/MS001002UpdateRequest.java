package com.brycen.hrm.masterservice.ms001002Update;

/**
 * [Description]: Update Request for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS001002UpdateRequest {

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
     * An user to correspond to a role
     */
    private Long roleID;

    /**
     * An user to correspond to an employee
     */
    private Long employeeID;

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

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

}
