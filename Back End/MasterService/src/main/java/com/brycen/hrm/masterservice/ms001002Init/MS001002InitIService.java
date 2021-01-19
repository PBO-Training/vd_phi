package com.brycen.hrm.masterservice.ms001002Init;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Initialize Service for details screen of User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS001002InitIService {

	/**
	 * [Description]: Find list employee and list role <br/>
	 * [ Remarks ]:<br/>
	 *
	 * @param companyID
	 * @return list employee
	 * @return list role
	 */
	BaseResponse init(int companyID);
}
