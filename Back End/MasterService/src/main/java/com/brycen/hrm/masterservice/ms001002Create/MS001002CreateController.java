package com.brycen.hrm.masterservice.ms001002Create;

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
 * [Description]: Create Controller for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS001002CreateController {

    /**
     * Call service to create an user
     */
    @Autowired
    MS001002CreateIService ms001002CreateIService;

    /**
     * Call log service to write log for api
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]:Create An User Account<br/>
     * [ Remarks ]:<br/>
     *
     * @param createUserRequest
     * @param request
     * @return Response data and status Created if success
     */
    @PostMapping(value = UrlAPI.MS001002_CREATE_USER)
    public ResponseEntity<?> ms001002CreateUser(@RequestBody MS001002CreateRequest createUserRequest, HttpServletRequest request) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS001002_CREATE_USER, createUserRequest, null, LogValue.BEGIN_API);
        int companyID = (int) request.getAttribute("companyID");
        BaseResponse baseRes = ms001002CreateIService.save(createUserRequest, companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS001002_CREATE_USER, createUserRequest, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }
}
