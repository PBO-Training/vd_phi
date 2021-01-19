package com.brycen.hrm.masterservice.ms003002Create;

public class MS003002CreateRequest {
	private String skillCode;
	private String skillName;
	private String skillDescription;

	private long skillTypeID;

	public MS003002CreateRequest() {
		super();
	}

	public MS003002CreateRequest(String skillCode, String skillName, String skillDescription, long skillTypeID) {
		super();
		this.skillCode = skillCode;
		this.skillName = skillName;
		this.skillDescription = skillDescription;
		this.skillTypeID = skillTypeID;
	}

	public String getSkillCode() {
		return skillCode;
	}

	public void setSkillCode(String skillCode) {
		this.skillCode = skillCode;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
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
