package com.brycen.hrm.masterservice.ms003001Search;

import com.brycen.hrm.common.base.BaseRequest;

/**
 * [Description]: Modal contain data need to receive from client to search<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS003001SearchRequest extends BaseRequest {
    private String skillName;
    private String skillCode;
    private Long skillTypeID;
    public MS003001SearchRequest(String skillName, String skillCode, Long skillTypeID) {
        super();
        this.skillName = skillName;
        this.skillCode = skillCode;
        this.skillTypeID = skillTypeID;
    }
 

    public Long getSkillTypeID() {
		return skillTypeID;
	}

	public void setSkillTypeID(Long skillTypeID) {
		this.skillTypeID = skillTypeID;
	}

	public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillCode() {
        return skillCode;
    }

    public void setSkillCode(String skillCode) {
        this.skillCode = skillCode;
    }
}
