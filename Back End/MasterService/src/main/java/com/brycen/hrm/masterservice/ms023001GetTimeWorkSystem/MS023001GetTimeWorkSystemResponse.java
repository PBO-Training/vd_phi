package com.brycen.hrm.masterservice.ms023001GetTimeWorkSystem;

import com.brycen.hrm.entity.SystemSettingEntity;

/**
 * [Description]: Search Response for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS023001GetTimeWorkSystemResponse {
	private String timeStartWorkAM;
	private String timeEndWorkAM;
	private String timeStartWorkPM;
	private String timeEndWorkPM;
	
    
    public MS023001GetTimeWorkSystemResponse(SystemSettingEntity SystemEnt) {
      this.timeStartWorkAM = SystemEnt.getTimeStartWorkAM();
      this.timeStartWorkPM = SystemEnt.getTimeStartWorkPM();
      this.timeEndWorkAM = SystemEnt.getTimeEndWorkAM();
      this.timeEndWorkPM = SystemEnt.getTimeEndWorkPM();
    }


	public String getTimeStartWorkAM() {
		return timeStartWorkAM;
	}


	public void setTimeStartWorkAM(String timeStartWorkAM) {
		this.timeStartWorkAM = timeStartWorkAM;
	}


	public String getTimeEndWorkAM() {
		return timeEndWorkAM;
	}


	public void setTimeEndWorkAM(String timeEndWorkAM) {
		this.timeEndWorkAM = timeEndWorkAM;
	}


	public String getTimeStartWorkPM() {
		return timeStartWorkPM;
	}


	public void setTimeStartWorkPM(String timeStartWorkPM) {
		this.timeStartWorkPM = timeStartWorkPM;
	}


	public String getTimeEndWorkPM() {
		return timeEndWorkPM;
	}


	public void setTimeEndWorkPM(String timeEndWorkPM) {
		this.timeEndWorkPM = timeEndWorkPM;
	}

	
    
}
