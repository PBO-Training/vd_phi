package com.brycen.hrm.masterservice.ms009001Delete;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.constant.LogValue;
import com.brycen.hrm.constant.UrlAPI;
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
public class MS009001DeleteController {
    @Autowired
    MS009001DeleteService deleteService;

    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Delete multiple customer<br/>
     * [ Remarks ]:<br/>
     *
     * @param request
     * @param servletReq
     * @return Response OK status if success
     */
    @DeleteMapping(value = UrlAPI.MS009001_DELETE_CUSTOMER)
    public ResponseEntity<?> deleteListCustomer(@RequestBody MS009001DeleteRequest request, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS009001_DELETE_CUSTOMER, request, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse result = deleteService.delete(request.getListDelete(), companyID);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS009001_DELETE_CUSTOMER, request, result, LogValue.END_API);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
