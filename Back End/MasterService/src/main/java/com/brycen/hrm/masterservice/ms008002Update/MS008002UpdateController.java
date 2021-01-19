package com.brycen.hrm.masterservice.ms008002Update;

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
import com.brycen.hrm.entity.StatusEmployeeEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Update Controller for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS008002UpdateController {

    /**
     * Call service to update employee status
     */
    @Autowired
    private MS008002UpdateIService ms008002UpdateIService;

    /**
     * Call log service to write log for api
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Update an employee status<br/>
     * [ Remarks ]:<br/>
     *
     * @param statusEmployeeEntity
     * @param servletReq
     * @return Response data and status Created if success
     */
    @PutMapping(value = UrlAPI.MS008002_UPDATE_EMPLOYEE_STATUS)
    public ResponseEntity<?> ms008002UpdateEmployeeStatus(@RequestBody StatusEmployeeEntity statusEmployeeEntity, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS008002_UPDATE_EMPLOYEE_STATUS, statusEmployeeEntity, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = ms008002UpdateIService.save(statusEmployeeEntity, companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS008002_UPDATE_EMPLOYEE_STATUS, statusEmployeeEntity, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }
}
