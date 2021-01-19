package com.brycen.hrm.masterservice.ms018002Create;

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
 * [Description]: Create controller for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS018002CreateController {

    /**
     * Call service to create a new role
     */
    @Autowired
    private MS018002CreateIService createSerivce;

    /**
     * Write log
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Create a new role<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms018002CreateRequest
     * @param servletReq
     * @return Content and error status
     */
    @PostMapping(value = UrlAPI.MS018002_CREATE_GROUP_ROLE)
    public ResponseEntity<?> ms018008createRole(@RequestBody MS018002CreateRequest ms018002CreateRequest, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS018002_CREATE_GROUP_ROLE, ms018002CreateRequest, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = createSerivce.save(ms018002CreateRequest, companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS018002_CREATE_GROUP_ROLE, ms018002CreateRequest, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }

}
