package com.brycen.hrm.masterservice.ms003002Create;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.constant.LogValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: this is place send response or receive request from client<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS003002CreateController {
	@Autowired
	private MS003002CreateService createSerivce;

	@Autowired
	private LoggerService logger;

	/**
	 * [Description]: Receive request by param in RequestBody and return message,
	 * status to client<br/>
	 * [ Remarks ]:<br/>
	 *
	 * @param skillEntity
	 * @return message success and status created if true
	 */
	@PostMapping(value = UrlAPI.MS003002_CREATE_SKILL)
	public ResponseEntity<?> ms003002CreateSkill(@RequestBody MS003002CreateRequest request,
			HttpServletRequest servletReq) {
		logger.write(LogLevel.INFOMATION, UrlAPI.MS003002_CREATE_SKILL, request, null, LogValue.BEGIN_API);
		int companyID = (int) servletReq.getAttribute("companyID");
		BaseResponse baseRes = createSerivce.save(request, companyID);
		HttpStatus status = baseRes.getError() == null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
		logger.write(LogLevel.INFOMATION, UrlAPI.MS003002_CREATE_SKILL, request, baseRes, LogValue.END_API);
		return new ResponseEntity<>(baseRes, status);
	}
}
