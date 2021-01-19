package com.brycen.hrm.masterservice.ms008001Delete;

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
 * [Description]: Delete Controller for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS008001DeleteController {

    /**
     * Call service layer to delete employee status
     */
    @Autowired
    MS008001DeleteIService ms008001DeleteIService;

    /**
     * Call log service to write log for api
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Delete multiple employee status<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms008001DeleteRequest
     * @param servletReq
     * @return Response OK status if success
     */
    @DeleteMapping(value = UrlAPI.MS008001_DELETE_EMPLOYEE_STATUS)
    public ResponseEntity<?> deleteListStatusEmployee(@RequestBody MS008001DeleteRequest ms008001DeleteRequest, HttpServletRequest servletReq) {
        BaseResponse baseRes = new BaseResponse();
        logger.write(LogLevel.INFOMATION, UrlAPI.MS008001_DELETE_EMPLOYEE_STATUS, ms008001DeleteRequest, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        int result = ms008001DeleteIService.delete(ms008001DeleteRequest.getListDelete(), companyID);
        baseRes.setContent(result);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS008001_DELETE_EMPLOYEE_STATUS, ms008001DeleteRequest, result, LogValue.END_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }

}
