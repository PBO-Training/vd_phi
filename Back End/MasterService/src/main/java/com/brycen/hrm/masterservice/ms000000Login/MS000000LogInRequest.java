package com.brycen.hrm.masterservice.ms000000Login;

public class MS000000LogInRequest {
    /**
     * CompanyCode of user
     */
    private String companyCode;
    
    /**
     * Username of user
     */
    private String username;

    /**
     * Password of user
     */
    private String password;

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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

}
