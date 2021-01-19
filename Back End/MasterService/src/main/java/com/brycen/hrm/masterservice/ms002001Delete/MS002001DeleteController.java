package com.brycen.hrm.masterservice.ms002001Delete;

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
 * [Description]: Delete Controller for User Master department Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@RestController
@CrossOrigin
public class MS002001DeleteController {
	/**
	 * Call service layer to delete department
	 */
	@Autowired
	MS002001DeleteIService ms002001deleteIService;

	@Autowired
	private LoggerService logger;

	/**
	 * [Description]: Delete multiple department<br/>
	 * [ Remarks ]:<br/>
	 *
	 * @param ms002001DeleteRequest
	 * @return Response OK status if success
	 */
	@DeleteMapping(value = UrlAPI.MS002001_DELETE_DEPARTMENT)
	public ResponseEntity<?> deleteListDepartment(@RequestBody MS002001DeleteRequest request,
			HttpServletRequest servletReq) {
		BaseResponse baseRes = new BaseResponse();
		logger.write(LogLevel.INFOMATION, UrlAPI.MS002001_DELETE_DEPARTMENT, request, null, LogValue.BEGIN_API);
		int companyID = (int) servletReq.getAttribute("companyID");
		int result = ms002001deleteIService.delete(request.getListDelete(), companyID);
		baseRes.setContent(result);
		logger.write(LogLevel.INFOMATION, UrlAPI.MS002001_DELETE_DEPARTMENT, request, baseRes, LogValue.END_API);
		return new ResponseEntity<>(baseRes, HttpStatus.OK);
	}
}
