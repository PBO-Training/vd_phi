	package com.brycen.hrm.masterservice.ms023001Search;

import com.brycen.hrm.common.base.BaseRequest;

/**
 * [Description]: Search Request for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS023001SearchRequest extends BaseRequest {

	/**
     * Shift Work name
     */
    private String shiftWorkOptionName;

    /**
     *  Shift Work code
     */
    private String shiftWorkOptionCode;
    
 
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

	

    

}
