package com.brycen.hrm.masterservice.ms023001Update;

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
import com.brycen.hrm.entity.ShiftWorkOptionEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Delete Controller for Scope Work Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@RestController
@CrossOrigin
public class MS023001UpdateController {

    /**
     * Call service to delete a list scope work
     */
    @Autowired
    MS023001UpdateIService updateIService;

    /**
     * Write log
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Delete a list scope work<br/>
     * [ Remarks ]:<br/>
     *
     * @param request
     * @param servletReq
     * @return Content and error status
     */
    @PostMapping(value = UrlAPI.MS023001_UPDATE_SHIFTWORKOPTION)
    public ResponseEntity<?> deleteListShiftWork(@RequestBody ShiftWorkOptionEntity request, HttpServletRequest servletReq) {
        BaseResponse baseRes = new BaseResponse();
        logger.write(LogLevel.INFOMATION, UrlAPI.MS023001_UPDATE_SHIFTWORKOPTION, request, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        baseRes = updateIService.update(request, companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS023001_UPDATE_SHIFTWORKOPTION, request, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }

}
