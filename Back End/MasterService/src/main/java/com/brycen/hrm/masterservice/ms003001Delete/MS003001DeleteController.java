package com.brycen.hrm.masterservice.ms003001Delete;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.constant.LogValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Delete Controller for User Master skill Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@RestController
@CrossOrigin
public class MS003001DeleteController {
	/**
	 * Call service layer to delete skill
	 */
	@Autowired
	MS003001DeleteIService ms003001deleteIService;

	@Autowired
	private LoggerService logger;

	/**
	 * [Description]: Delete multiple skill<br/>
	 * [ Remarks ]:<br/>
	 *
	 * @param ms003001DeleteRequest
	 * @return Response OK status if success
	 */
	@DeleteMapping(value = UrlAPI.MS003001_DELETE_SKILL)
	public ResponseEntity<?> deleteListSkill(@RequestBody MS003001DeleteRequest request,
			HttpServletRequest servletReq) {
		BaseResponse baseRes = new BaseResponse();
		logger.write(LogLevel.INFOMATION, UrlAPI.MS003001_DELETE_SKILL, request, null, LogValue.BEGIN_API);
		int companyID = (int) servletReq.getAttribute("companyID");
		int result = ms003001deleteIService.delete(request.getListDelete(), companyID);
		baseRes.setContent(result);
		logger.write(LogLevel.INFOMATION, UrlAPI.MS003001_DELETE_SKILL, request, baseRes, LogValue.END_API);
		return new ResponseEntity<>(baseRes, HttpStatus.OK);
	}
}
