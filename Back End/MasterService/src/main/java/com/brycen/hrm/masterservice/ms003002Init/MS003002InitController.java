package com.brycen.hrm.masterservice.ms003002Init;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.constant.LogValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Init Controller for Skill Type Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@RestController
@CrossOrigin
public class MS003002InitController {

	/**
	 * Support write log
	 */
	@Autowired
	private LoggerService logger;

	/**
	 * Call service to support initialize
	 */
	@Autowired
	private MS003002InitIService ms003002InitIService;

	/**
	 * [Description]: Initialize data for drop-down list in Skill Type<br/>
	 * [ Remarks ]:<br/>
	 *
	 * @param req
	 * @return Content and error status
	 */
	@PostMapping(value = UrlAPI.MS003002_INIT_SKILL)
	public ResponseEntity<?> ms003002Init(HttpServletRequest req) {
		logger.write(LogLevel.INFOMATION, UrlAPI.MS003002_INIT_SKILL, null, null, LogValue.BEGIN_API);
		int companyID = (int) req.getAttribute("companyID");
		BaseResponse baseResponse = ms003002InitIService.init(companyID, req);
		logger.write(LogLevel.INFOMATION, UrlAPI.MS003002_INIT_SKILL, null, baseResponse, LogValue.END_API);
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}

}
