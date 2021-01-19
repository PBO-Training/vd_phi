package com.brycen.hrm.masterservice.ms020001Search;

import com.brycen.hrm.common.base.BaseRequest;

/**
 * [Description]: Modal contain data need to receive from client to search<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS020001SearchRequest extends BaseRequest {
    private String degreeName;
    private String degreeCode;

    public MS020001SearchRequest(String degreeName, String degreeCode) {
        super();
        this.degreeName = degreeName;
        this.degreeCode = degreeCode;
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
}
