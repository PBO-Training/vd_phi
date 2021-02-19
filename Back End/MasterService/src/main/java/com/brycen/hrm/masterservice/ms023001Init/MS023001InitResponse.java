package com.brycen.hrm.masterservice.ms023001Init;

import com.brycen.hrm.entity.SystemSettingEntity;

/**
 * [Description]: Search Response for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS023001InitResponse {
	private long stepBreakTime;
    
    public MS023001InitResponse(SystemSettingEntity stepBreakTime) {
       this.stepBreakTime = stepBreakTime.getStepBreakTime();
    }

	public long getStepBreakTime() {
		return stepBreakTime;
	}

	public void setStepBreakTime(long stepBreakTime) {
		this.stepBreakTime = stepBreakTime;
	}
    
}
