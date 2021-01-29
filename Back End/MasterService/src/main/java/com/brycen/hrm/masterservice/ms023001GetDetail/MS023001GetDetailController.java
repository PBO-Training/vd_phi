package com.brycen.hrm.masterservice.ms023001GetDetail;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.ShiftWorkOptionEntity;
@CrossOrigin
@RestController
public class MS023001GetDetailController {
	@Autowired
	MS023001GetDetailService getDetailService;
	
	@PostMapping(value = UrlAPI.MS023001_GETDETAIL_SHIFTWORKOPTION)
	public ResponseEntity<?> ms023001GetDetail (@RequestBody ShiftWorkOptionEntity shiftworkOptionEntity , HttpServletRequest servletReq){
		int companyID = (int) servletReq.getAttribute("companyID");
		BaseResponse baseRes = getDetailService.getDetail(shiftworkOptionEntity.getShiftWorkOptionID(), companyID);
		HttpStatus status = baseRes.getError() == null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
		return new ResponseEntity<>(baseRes, status);
		
	}
}
