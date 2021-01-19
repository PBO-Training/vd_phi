package com.brycen.hrm.masterservice.ms010002Create;

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
import com.brycen.hrm.entity.PositionProjectEntity;
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
public class MS010002CreateController {
    /**
     * Call to Service and get methods to do actions Create new project position
     */
    @Autowired
    private MS010002CreateService createSerivce;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Receive request by param in RequestBody and return message, status to client<br/>
     * [ Remarks ]:<br/>
     *
     * @param projectPositionEntity
     * @return message success and status created if true
     */
    @PostMapping(value = UrlAPI.MS010002_CREATE_POSITION_PROJECT)
    public ResponseEntity<?> ms010002CreatePosition(@RequestBody PositionProjectEntity positionProjectEntity, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS010002_CREATE_POSITION_PROJECT, positionProjectEntity, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = createSerivce.save(positionProjectEntity, companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS010002_CREATE_POSITION_PROJECT, positionProjectEntity, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }
}
