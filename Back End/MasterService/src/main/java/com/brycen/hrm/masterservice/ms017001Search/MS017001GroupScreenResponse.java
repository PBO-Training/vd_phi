package com.brycen.hrm.masterservice.ms017001Search;

import com.brycen.hrm.entity.GroupScreenEntity;

public class MS017001GroupScreenResponse {
    
    private String groupScreenCode;

    public MS017001GroupScreenResponse() {
        super();
    }

    public MS017001GroupScreenResponse(GroupScreenEntity groupScreenEntity) {
        super();
        this.groupScreenCode = groupScreenEntity.getGroupScreenCode();
    }

    public String getGroupScreenCode() {
        return groupScreenCode;
    }

    public void setGroupScreenCode(String groupScreenCode) {
        this.groupScreenCode = groupScreenCode;
    }    
}
