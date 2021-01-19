package com.brycen.hrm.masterservice.ms013002Update;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
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
public class MS013002UpdateController {
    @Autowired
    private MS013002UpdateService updateService;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Receive request by param in RequestBody and return message and status to client<br/>
     * [ Remarks ]:<br/>
     *
     * @param holidayRequest
     * @return message success and status OK if true
     */
    @PutMapping(value = UrlAPI.MS013002_UPDATE_HOLIDAY)
    public ResponseEntity<?> ms013002UpdateHoliday(@RequestBody MS013002UpdateRequest request, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS013002_UPDATE_HOLIDAY, request, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = updateService.save(request, companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS013002_UPDATE_HOLIDAY, request, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }
}
