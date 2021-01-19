package com.brycen.hrm.masterservice.ms003002Update;

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
public class MS003002UpdateController {
    @Autowired
    private MS003002UpdateService updateService;

    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Receive request by param in RequestBody and return message and status to client<br/>
     * [ Remarks ]:<br/>
     *
     * @param skillRequest
     * @return message success and status OK if true
     */
    @PutMapping(value = UrlAPI.MS003002_UPDATE_SKILL)
    public ResponseEntity<?> ms003002UpdateSkill(@RequestBody MS003002UpdateRequest request, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS003002_UPDATE_SKILL, request, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = updateService.save(request, companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS003002_UPDATE_SKILL, request, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }
}
