package com.brycen.hrm.masterservice.ms020002GetDetail;

import com.brycen.hrm.entity.DegreeEntity;

public class MS020002GetDetailResponse {
    private long degreeID;
    private String degreeName;
    private String degreeCode;
    private String degreeDescription;

    public MS020002GetDetailResponse() {
        super();
    }

    public MS020002GetDetailResponse(DegreeEntity degreeEntity) {
        super();
        this.degreeID = degreeEntity.getDegreeID();
        this.degreeName = degreeEntity.getDegreeName();
        this.degreeCode = degreeEntity.getDegreeCode();
        this.degreeDescription = degreeEntity.getDegreeDescription();
    }

    /**
     * @return the degreeID
     */
    public long getDegreeID() {
        return degreeID;
    }

    /**
     * @param degreeID the degreeID to set
     */
    public void setDegreeID(long degreeID) {
        this.degreeID = degreeID;
    }

    /**
     * @return the degreeName
     */
    public String getDegreeName() {
        return degreeName;
    }

    /**
     * @param degreeName the degreeName to set
     */
    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    /**
     * @return the degreeCode
     */
    public String getDegreeCode() {
        return degreeCode;
    }

    /**
     * @param degreeCode the degreeCode to set
     */
    public void setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode;
    }

    /**
     * @return the degreeDescription
     */
    public String getDegreeDescription() {
        return degreeDescription;
    }

    /**
     * @param degreeDescription the degreeDescription to set
     */
    public void setDegreeDescription(String degreeDescription) {
        this.degreeDescription = degreeDescription;
    }

}
