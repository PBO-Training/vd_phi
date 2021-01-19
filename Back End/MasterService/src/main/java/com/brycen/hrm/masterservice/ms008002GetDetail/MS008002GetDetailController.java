package com.brycen.hrm.masterservice.ms008002GetDetail;

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
 * [Description]: Get Details Controller for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS008002GetDetailController {

    /**
     * Call service layer to get details employee status
     */
    @Autowired
    private MS008002GetDetailIService ms008002GetDetailIService;

    /**
     * Call log service to write log for api
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Get details a employee status<br/>
     * [ Remarks ]:<br/>
     *
     * @param statusEmployeeEntity
     * @param servletReq
     * @return Response OK status if success
     */
    @PostMapping(value = UrlAPI.MS008002_GETDETAIL_EMPLOYEE_STATUS)
    public ResponseEntity<?> ms008002DetailEmployeeStatus(@RequestBody StatusEmployeeEntity statusEmployeeEntity, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS008002_GETDETAIL_EMPLOYEE_STATUS, statusEmployeeEntity, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = ms008002GetDetailIService.getDetail(statusEmployeeEntity.getStatusEmployeeID(), companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS008002_GETDETAIL_EMPLOYEE_STATUS, statusEmployeeEntity, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }

}
