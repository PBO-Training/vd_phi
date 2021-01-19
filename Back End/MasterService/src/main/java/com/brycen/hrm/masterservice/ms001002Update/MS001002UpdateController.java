package com.brycen.hrm.masterservice.ms001002Update;

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
 * [Description]: Update Controller for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@RestController
@CrossOrigin
public class MS001002UpdateController {

    /**
     * Call service layer to update an user
     */
    @Autowired
    MS001002UpdateIService ms001002UpdateIService;

    /**
     * Call log service to write log for api
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Update User by UserID<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms001002UpdateRequest
     * @return Response OK status if success
     */
    @PutMapping(value = UrlAPI.MS001002_UPDATE_USER)
    public ResponseEntity<?> updateUser(@RequestBody MS001002UpdateRequest ms001002UpdateRequest, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS001002_UPDATE_USER, ms001002UpdateRequest, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = ms001002UpdateIService.updateUser(ms001002UpdateRequest, companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS001002_UPDATE_USER, ms001002UpdateRequest, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }

}
