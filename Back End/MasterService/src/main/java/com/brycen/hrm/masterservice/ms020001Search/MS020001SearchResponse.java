package com.brycen.hrm.masterservice.ms020001Search;

import com.brycen.hrm.entity.DegreeEntity;

/**
 * [Description]: Create constructure of List<Object> in ContentResponse. This is data who client want to see<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS020001SearchResponse {
    private long degreeID;
    private String degreeName;
    private String degreeCode;
    private String degreeDescription;

    public MS020001SearchResponse() {
        super();
    }

    public MS020001SearchResponse(DegreeEntity degreeEntity) {
        super();
        this.degreeID = degreeEntity.getDegreeID();
        this.degreeName = degreeEntity.getDegreeName();
        this.degreeCode = degreeEntity.getDegreeCode();
        this.degreeDescription = degreeEntity.getDegreeDescription();
    }

    public long getDegreeID() {
        return degreeID;
    }

    public void setDegreeID(long degreeID) {
        this.degreeID = degreeID;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getDegreeCode() {
        return degreeCode;
    }

    public void setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode;
    }

    public String getDegreeDescription() {
        return degreeDescription;
    }

    public void setDegreeDescription(String degreeDescription) {
        this.degreeDescription = degreeDescription;
    }
}
