package com.brycen.hrm.masterservice.ms010002GetDetail;

import com.brycen.hrm.entity.PositionProjectEntity;

public class MS010002GetDetailResponse {
    private long positionProjectID;
    private String positionProjectName;
    private String positionProjectCode;
    private String positionProjectDescription;

    public MS010002GetDetailResponse() {
        super();
    }

    public MS010002GetDetailResponse(PositionProjectEntity positionProjectEntity) {
        super();
        this.positionProjectID = positionProjectEntity.getPositionProjectID();
        this.positionProjectName = positionProjectEntity.getPositionProjectName();
        this.positionProjectCode = positionProjectEntity.getPositionProjectCode();
        this.positionProjectDescription = positionProjectEntity.getPositionProjectDescription();
    }

    /**
     * @return the positionProjectID
     */
    public long getPositionProjectID() {
        return positionProjectID;
    }

    /**
     * @param positionProjectID the positionProjectID to set
     */
    public void setPositionProjectID(long positionProjectID) {
        this.positionProjectID = positionProjectID;
    }

    /**
     * @return the positionProjectName
     */
    public String getPositionProjectName() {
        return positionProjectName;
    }

    /**
     * @param positionProjectName the positionProjectName to set
     */
    public void setPositionProjectName(String positionProjectName) {
        this.positionProjectName = positionProjectName;
    }

    /**
     * @return the positionProjectCode
     */
    public String getPositionProjectCode() {
        return positionProjectCode;
    }

    /**
     * @param positionProjectCode the positionProjectCode to set
     */
    public void setPositionProjectCode(String positionProjectCode) {
        this.positionProjectCode = positionProjectCode;
    }

    /**
     * @return the positionProjectDescription
     */
    public String getPositionProjectDescription() {
        return positionProjectDescription;
    }

    /**
     * @param positionProjectDescription the positionProjectDescription to set
     */
    public void setPositionProjectDescription(String positionProjectDescription) {
        this.positionProjectDescription = positionProjectDescription;
    }

}
