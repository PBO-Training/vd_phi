package com.brycen.hrm.masterservice.ms000000Login;

/**
 * [Description]:Login Response<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS000000LogInResponse {
    /**
     * Access Token
     */
    private String token;

    /**
     * Token Type : Bearer
     */
    private String type = "Bearer";

    /**
     * User ID
     */
    private Long userID;

    /**
     * Username of User
     */
    private String username;
    // private List<String> roles;
    // private List<Integer> roleValues;
    /**
     * Employee ID of User
     */
    private Long employeeID;

    /**
     * Full name of User
     */
    private String employeeName;

    /**
     * Avata URL of User
     */
    private String avataURL;

    /**
     * Company Code of User
     */
    private String companyCode;

    /**
     * Company Name of User
     */
    private String companyName;
    
    /**
     * List access Screen of Role user
     */
    private Object role;

    public MS000000LogInResponse(String accessToken, Long userID, String username, Long employeeID, String employeeName, String avataURL, Object role,
            String companyCode, String companyName) {
        this.token = accessToken;
        this.userID = userID;
        this.username = username;
        // this.roleValues = roleValues;
        // this.roles = roles;
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.avataURL = avataURL;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.role = role;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
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

    // public List<Integer> getRoleValues() {
    // return roleValues;
    // }
    //
    // public void setRoleValues(List<Integer> roleValues) {
    // this.roleValues = roleValues;
    // }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getAvataURL() {
        return avataURL;
    }

    public void setAvataURL(String avataURL) {
        this.avataURL = avataURL;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        this.role = role;
    }

    // public List<String> getRoles() {
    // return roles;
    // }
}
