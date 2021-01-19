package com.brycen.hrm.masterservice.ms003002Update;

public class MS003002UpdateRequest {
	private long skillID;
	private String skillCode;
	private String skillName;
	private String skillDescription;
	private long skillTypeID;

	public MS003002UpdateRequest() {
		super();
	}

	public MS003002UpdateRequest(long skillID, String skillCode, String skillName, String skillDescription,
			long skillTypeID) {
		super();
		this.skillID = skillID;
		this.skillCode = skillCode;
		this.skillName = skillName;
		this.skillDescription = skillDescription;
		this.skillTypeID = skillTypeID;
	}

	public long getSkillID() {
		return skillID;
	}

	public void setSkillID(long skillID) {
		this.skillID = skillID;
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
