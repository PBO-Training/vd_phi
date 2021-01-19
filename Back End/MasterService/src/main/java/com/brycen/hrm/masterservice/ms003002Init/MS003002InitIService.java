package com.brycen.hrm.masterservice.ms003002Init;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Init Service for Skill Type Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS003002InitIService {

	/**
	 * [Description]: Initialize data for drop down list in Skill Type Master
	 * Table<br/>
	 * [ Remarks ]:<br/>
	 *
	 * @param companyID
	 * @param req
	 * @return data of drop down list
	 */
	BaseResponse init(int companyID, HttpServletRequest req);

}
