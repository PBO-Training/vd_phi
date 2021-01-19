package com.brycen.hrm.masterservice.ms003002GetDetail;

import com.brycen.hrm.entity.SkillEntity;

public class MS003002GetDetailResponse {
	private long skillID;
	private String skillName;
	private String skillCode;
	private String skillDescription;
	private long skillTypeID;

	public MS003002GetDetailResponse() {
		super();
	}

	public MS003002GetDetailResponse(SkillEntity skillEntity) {
		super();
		this.skillID = skillEntity.getSkillID();
		this.skillName = skillEntity.getSkillName();
		this.skillCode = skillEntity.getSkillCode();
		this.skillDescription = skillEntity.getSkillDescription();
		if (skillEntity.getSkillType() != null && skillEntity.getSkillType().getIsDelete() == false) {
			this.skillTypeID = skillEntity.getSkillType().getSkillTypeID();
		} else {
			this.skillTypeID = 0;
		}
	}

	public long getSkillID() {
		return skillID;
	}

	public void setSkillID(long skillID) {
		this.skillID = skillID;
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

	public String getSkillDescription() {
		return skillDescription;
	}

	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}

	public long getSkillTypeID() {
		return skillTypeID;
	}

	public void setSkillTypeID(long skillTypeID) {
		this.skillTypeID = skillTypeID;
	}
}
