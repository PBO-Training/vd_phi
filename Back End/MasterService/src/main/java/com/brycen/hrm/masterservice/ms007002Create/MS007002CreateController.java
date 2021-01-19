package com.brycen.hrm.masterservice.ms007002Create;

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
import com.brycen.hrm.entity.PositionEmployeeEntity;
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
public class MS007002CreateController {
    @Autowired
    private MS007002CreateService createSerivce;

    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Receive request by param in RequestBody and return message, status to client<br/>
     * [ Remarks ]:<br/>
     *
     * @param positionEmployeeEntity
     * @return message success and status created if true
     */
    @PostMapping(value = UrlAPI.MS007002_CREATE_EMPLOYEE_POSITION)
    public ResponseEntity<?> ms007002CreatePosition(@RequestBody PositionEmployeeEntity positionEmployeeEntity, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS007002_CREATE_EMPLOYEE_POSITION, positionEmployeeEntity, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = createSerivce.save(positionEmployeeEntity, companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS007002_CREATE_EMPLOYEE_POSITION, positionEmployeeEntity, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }
}
