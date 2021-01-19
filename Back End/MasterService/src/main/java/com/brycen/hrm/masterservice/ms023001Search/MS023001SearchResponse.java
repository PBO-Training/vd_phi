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
    private Long shiftWorkOptionID;

    /**
     *  Shift Work name
     */
    private String shiftWorkOptionName;

    /**
     *  Shift Work code
     */
    private String shiftWorkOptionCode;
    
    /**
     *  Shift Work Start Time AM
     */
    private String shiftWorkOptionStartTimeAM;
    
    /**
     *  Shift Work End Time AM
     */
    private String shiftWorkOptionEndTimeAM;
    
    /**
     *  Shift Work Start Time PM
     */
    private String shiftWorkOptionStartTimePM;
    
    /**
     *  Shift Work End Time PM
     */
    private String shiftWorkOptionEndTimePM;
    

	/**
     *  Shift Work Description
     */
    private String shiftWorkOptionDescription;
    
    public MS023001SearchResponse(ShiftWorkOptionEntity shiftWorkEntity) {
        super();
        this.shiftWorkOptionCode = shiftWorkEntity.getShiftWorkOptionCode();
        this.shiftWorkOptionID = shiftWorkEntity.getShiftWorkOptionID();
        this.shiftWorkOptionName = shiftWorkEntity.getShiftWorkOptionName();
        this.shiftWorkOptionStartTimeAM = shiftWorkEntity.getShiftWorkOptionStartTimeAM();
        this.shiftWorkOptionEndTimeAM =  shiftWorkEntity.getShiftWorkOptionEndTimeAM();
        this.shiftWorkOptionStartTimePM = shiftWorkEntity.getShiftWorkOptionStartTimePM();
        this.shiftWorkOptionEndTimePM = shiftWorkEntity.getShiftWorkOptionEndTimePM();
        this.shiftWorkOptionDescription = shiftWorkEntity.getShiftWorkOptionDescription();
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

	public String getShiftWorkOptionStartTimeAM() {
		return shiftWorkOptionStartTimeAM;
	}

	public void setShiftWorkOptionStartTimeAM(String shiftWorkOptionStartTimeAM) {
		this.shiftWorkOptionStartTimeAM = shiftWorkOptionStartTimeAM;
	}

	public String getShiftWorkOptionEndTimeAM() {
		return shiftWorkOptionEndTimeAM;
	}

	public void setShiftWorkOptionEndTimeAM(String shiftWorkOptionEndTimeAM) {
		this.shiftWorkOptionEndTimeAM = shiftWorkOptionEndTimeAM;
	}

	public String getShiftWorkOptionStartTimePM() {
		return shiftWorkOptionStartTimePM;
	}

	public void setShiftWorkOptionStartTimePM(String shiftWorkOptionStartTimePM) {
		this.shiftWorkOptionStartTimePM = shiftWorkOptionStartTimePM;
	}

	public String getShiftWorkOptionEndTimePM() {
		return shiftWorkOptionEndTimePM;
	}

	public void setShiftWorkOptionEndTimePM(String shiftWorkOptionEndTimePM) {
		this.shiftWorkOptionEndTimePM = shiftWorkOptionEndTimePM;
	}

	public String getShiftWorkOptionDescription() {
		return shiftWorkOptionDescription;
	}
	public void setShiftWorkOptionDescription(String shiftWorkOptionDescription) {
		this.shiftWorkOptionDescription = shiftWorkOptionDescription;
	} 

}
