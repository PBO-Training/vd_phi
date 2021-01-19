package com.brycen.hrm.masterservice.ms010001Search;

import com.brycen.hrm.common.base.BaseRequest;

public class MS010001SearchRequest extends BaseRequest {
    /**
     * positionProject code
     */
    private String positionProjectCode;
    /**
     * positionProject name
     */
    private String positionProjectName;

    public MS010001SearchRequest() {
        super();
    }

    public MS010001SearchRequest(String positionProjectCode, String positionProjectName) {
        super();
        this.positionProjectCode = positionProjectCode;
        this.positionProjectName = positionProjectName;
    }

    public String getPositionProjectCode() {
        return positionProjectCode;
    }

    public void setPositionProjectCode(String positionProjectCode) {
        this.positionProjectCode = positionProjectCode;
    }

    public String getPositionProjectName() {
        return positionProjectName;
    }

    public void setPositionProjectName(String positionProjectName) {
        this.positionProjectName = positionProjectName;
    }
}
