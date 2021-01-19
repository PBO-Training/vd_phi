package com.brycen.hrm.masterservice.ms003002Init;

import com.brycen.hrm.entity.SkillTypeEntity;

/**
 * [Description]: Initialize Response for Skill Type Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS003002InitResponse {

	private long skillTypeID;

	private String skillTypeName;

	private String skillTypeCode;

	private String skillTypeDescription;

	public MS003002InitResponse() {
		super();
	}

	public MS003002InitResponse(SkillTypeEntity skillTypeEntity) {
		super();
		this.skillTypeID = skillTypeEntity.getSkillTypeID();
		this.skillTypeName = skillTypeEntity.getSkillTypeName();
		this.skillTypeCode = skillTypeEntity.getSkillTypeCode();
		this.skillTypeDescription = skillTypeEntity.getSkillTypeDescription();
	}

	public long getSkillTypeID() {
		return skillTypeID;
	}

	public void setSkillTypeID(long skillTypeID) {
		this.skillTypeID = skillTypeID;
	}

	public String getSkillTypeName() {
		return skillTypeName;
	}

	public void setSkillTypeName(String skillTypeName) {
		this.skillTypeName = skillTypeName;
	}

	public String getSkillTypeCode() {
		return skillTypeCode;
	}

	public void setSkillTypeCode(String skillTypeCode) {
		this.skillTypeCode = skillTypeCode;
	}

	public String getSkillTypeDescription() {
		return skillTypeDescription;
	}

	public void setSkillTypeDescription(String skillTypeDescription) {
		this.skillTypeDescription = skillTypeDescription;
	}

}
