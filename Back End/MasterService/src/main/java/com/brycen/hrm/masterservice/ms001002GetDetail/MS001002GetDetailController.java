package com.brycen.hrm.masterservice.ms001002GetDetail;

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
import com.brycen.hrm.entity.UserEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Get Details Controller for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@RestController
@CrossOrigin
public class MS001002GetDetailController {

    /**
     * Call service layer to get details an user
     */
    @Autowired
    MS001002GetDetailIService ms001002GetDetailIService;

    /**
     * Call log service to write log for api
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Get details an user<br/>
     * [ Remarks ]:<br/>
     *
     * @param userEntity
     * @param servletReq
     * @return Response OK status if success
     */
    @PostMapping(value = UrlAPI.MS001002_GETDETAIL_USER)
    public ResponseEntity<?> userDetails(@RequestBody UserEntity userEntity, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS001002_GETDETAIL_USER, userEntity, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = ms001002GetDetailIService.findByID(userEntity.getUserID(), companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS001002_GETDETAIL_USER, userEntity, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }

}
