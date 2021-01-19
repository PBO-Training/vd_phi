package com.brycen.hrm.masterservice.ms009002Create;

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
import com.brycen.hrm.entity.CustomerEntity;
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
public class MS009002CreateController {
    @Autowired
    private MS009002CreateService createService;
    
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Receive request by param in RequestBody and return message, status to client<br/>
     * [ Remarks ]:<br/>
     *
     * @param customerEntity
     * @return message success and status created if true
     */
    @PostMapping(value = UrlAPI.MS009002_CREATE_CUSTOMER)
    public ResponseEntity<?> ms009002CreateCustomer(HttpServletRequest servletReq, @RequestBody CustomerEntity customerEntity) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS009002_CREATE_CUSTOMER, customerEntity, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        System.out.println(companyID);
        BaseResponse baseRes = createService.save(customerEntity, companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS009002_CREATE_CUSTOMER, customerEntity, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }
}
