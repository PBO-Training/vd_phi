package com.brycen.hrm.masterservice.ms023001Search;

import com.brycen.hrm.entity.ShiftWorkOptionEntity;

/**
 * [Description]: Search Response for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS023001SearchResponse {

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
    
    /**
     *  Shift Work time
     */
    private String shiftWorkOptionTime;
    
	/**
     *  Shift Work Description
     */
    private String shiftWorkOptionDescription;
    
    public MS023001SearchResponse(ShiftWorkOptionEntity shiftWorkEntity) {
        this.shiftWorkOptionCode = shiftWorkEntity.getShiftWorkOptionCode();
        this.shiftWorkOptionID = shiftWorkEntity.getShiftWorkOptionID();
        this.shiftWorkOptionName = shiftWorkEntity.getShiftWorkOptionName();
        this.shiftWorkOptionDescription = shiftWorkEntity.getShiftWorkOptionDescription();
        this.shiftWorkOptionTime = shiftWorkEntity.getShiftWorkOptionStartTimeAM()+ " ~ " + shiftWorkEntity.getShiftWorkOptionEndTimeAM()
        +" | "+ shiftWorkEntity.getShiftWorkOptionStartTimePM()+" ~ "+ shiftWorkEntity.getShiftWorkOptionEndTimePM();
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

	public String getShiftWorkOptionTime() {
		return shiftWorkOptionTime;
	}
	public void setShiftWorkOptionTime(String shiftWorkOptionTime) {
		this.shiftWorkOptionTime = shiftWorkOptionTime;
	}
	public String getShiftWorkOptionDescription() {
		return shiftWorkOptionDescription;
	}
	public void setShiftWorkOptionDescription(String shiftWorkOptionDescription) {
		this.shiftWorkOptionDescription = shiftWorkOptionDescription;
	} 

}
