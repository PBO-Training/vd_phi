package com.brycen.hrm.masterservice.ms008002Create;

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
import com.brycen.hrm.entity.StatusEmployeeEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Create Controller for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS008002CreateController {

    /**
     * Call service to create employee status
     */
    @Autowired
    private MS008002CreateIService ms008002CreateIService;

    /**
     * Call log service to write log for api
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Create a employee status<br/>
     * [ Remarks ]:<br/>
     *
     * @param servletReq
     * @param statusEmployeeEntity
     * @return Response data and status Created if success
     */
    @PostMapping(value = UrlAPI.MS008002_CREATE_EMPLOYEE_STATUS)
    public ResponseEntity<?> ms008002CreateEmployeeStatus(HttpServletRequest servletReq, @RequestBody StatusEmployeeEntity statusEmployeeEntity) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS008002_CREATE_EMPLOYEE_STATUS, statusEmployeeEntity, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        System.out.println(companyID);
        BaseResponse baseRes = ms008002CreateIService.save(statusEmployeeEntity, companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS008002_CREATE_EMPLOYEE_STATUS, statusEmployeeEntity, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }

}
