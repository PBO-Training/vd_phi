package com.brycen.hrm.masterservice.ms001002Create;

import com.brycen.hrm.entity.UserEntity;

public class MS001002CreateResponse {

    /**
     * UserID
     */
    private Long userID;

    /**
     * Username
     */
    private String username;

    public MS001002CreateResponse() {
        super();
    }

    public MS001002CreateResponse(UserEntity userEntity) {
        super();
        this.userID = userEntity.getUserID();
        this.username = userEntity.getUsername();
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

}
