package com.brycen.hrm.masterservice.ms003001Init;

import com.brycen.hrm.common.base.BaseInitResponse;
import com.brycen.hrm.entity.SkillTypeEntity;

/**
 * [Description]: Base Response for User Skill Type Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS003001InitBaseResponse extends BaseInitResponse {

	public MS003001InitBaseResponse() {

	}

	public MS003001InitBaseResponse(SkillTypeEntity entity) {
		this.setKeyResponse(entity.getSkillTypeID());
		this.setValueResponse(entity.getSkillTypeName());
	}

	public MS003001InitBaseResponse(Long skillTypeID, String skillTypeName) {
		this.setKeyResponse(skillTypeID);
		this.setValueResponse(skillTypeName);
	}
}
