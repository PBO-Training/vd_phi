package com.brycen.hrm.masterservice.ms023001GetDetail;

import com.brycen.hrm.entity.ShiftWorkOptionEntity;

public class MS023001GetDetailResponse {
	 /**
     *  Shift Work ID
     */
    private long shiftWorkOptionID;

    /**
     *  Shift Work name
     */
    private String shiftWorkOptionName;

    /**
     *  Shift Work code
     */
    private String shiftWorkOptionCode;
    
    private String shiftWorkOptionTimeStartAM;
    
    private String shiftWorkOptionTimeEndAM;
    
    private String shiftWorkOptionTimeStartPM;
    
    private String shiftWorkOptionTimeEndPM;
	/**
     *  Shift Work Description
     */
    private String shiftWorkOptionDescription;
    
    public MS023001GetDetailResponse(ShiftWorkOptionEntity shiftWorkEntity) {
        this.shiftWorkOptionCode = shiftWorkEntity.getShiftWorkOptionCode();
        this.shiftWorkOptionID = shiftWorkEntity.getShiftWorkOptionID();
        this.shiftWorkOptionName = shiftWorkEntity.getShiftWorkOptionName();
        this.shiftWorkOptionDescription = shiftWorkEntity.getShiftWorkOptionDescription();
        this.shiftWorkOptionTimeStartAM = shiftWorkEntity.getShiftWorkOptionStartTimeAM();
        this.shiftWorkOptionTimeEndAM = shiftWorkEntity.getShiftWorkOptionEndTimeAM();
        this.shiftWorkOptionTimeStartPM = shiftWorkEntity.getShiftWorkOptionStartTimePM();
        this.shiftWorkOptionTimeEndPM = shiftWorkEntity.getShiftWorkOptionEndTimePM();
        
    }
    public Long getShiftWorkOptionID() {
		return shiftWorkOptionID;
	}

	public void setShiftWorkOptionID(Long shiftWorkOptionID) {
		this.shiftWorkOptionID = shiftWorkOptionID;
	}

	public String getShiftWorkOptionName() {
		return shiftWorkOptionName;
	}

	public void setShiftWorkOptionName(String shiftWorkOptionName) {
		this.shiftWorkOptionName = shiftWorkOptionName;
	}

	public String getShiftWorkOptionCode() {
		return shiftWorkOptionCode;
	}

	public void setShiftWorkOptionCode(String shiftWorkOptionCode) {
		this.shiftWorkOptionCode = shiftWorkOptionCode;
	}
	
	public String getShiftWorkOptionTimeStartAM() {
		return shiftWorkOptionTimeStartAM;
	}
	public void setShiftWorkOptionTimeStartAM(String shiftWorkOptionTimeStartAM) {
		this.shiftWorkOptionTimeStartAM = shiftWorkOptionTimeStartAM;
	}
	public String getShiftWorkOptionTimeEndAM() {
		return shiftWorkOptionTimeEndAM;
	}
	public void setShiftWorkOptionTimeEndAM(String shiftWorkOptionTimeEndAM) {
		this.shiftWorkOptionTimeEndAM = shiftWorkOptionTimeEndAM;
	}
	public String getShiftWorkOptionTimeStartPM() {
		return shiftWorkOptionTimeStartPM;
	}
	public void setShiftWorkOptionTimeStartPM(String shiftWorkOptionTimeStartPM) {
		this.shiftWorkOptionTimeStartPM = shiftWorkOptionTimeStartPM;
	}
	public String getShiftWorkOptionTimeEndPM() {
		return shiftWorkOptionTimeEndPM;
	}
	public void setShiftWorkOptionTimeEndPM(String shiftWorkOptionTimeEndPM) {
		this.shiftWorkOptionTimeEndPM = shiftWorkOptionTimeEndPM;
	}
	public String getShiftWorkOptionDescription() {
		return shiftWorkOptionDescription;
	}
	public void setShiftWorkOptionDescription(String shiftWorkOptionDescription) {
		this.shiftWorkOptionDescription = shiftWorkOptionDescription;
	} 
}
