package com.brycen.hrm.masterservice.ms008001Search;

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
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Search Controller for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS008001SearchController {

    /**
     * Call service layer to search employee status
     */
    @Autowired
    private MS008001SearchIService ms008001SearchIService;

    /**
     * Call log service to write log for api
     */
    @Autowired
    LoggerService logger;

    /**
     * [Description]: Search Employee Status with multiple values<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms008001SearchRequest
     * @param servletReq
     * @return OK if success
     */
    @PostMapping(value = UrlAPI.MS008001_SEARCH_EMPLOYEE_STATUS)
    public ResponseEntity<?> ms008001SearchStatusEmployee(@RequestBody MS008001SearchRequest ms008001SearchRequest, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS008001_SEARCH_EMPLOYEE_STATUS, ms008001SearchRequest, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = ms008001SearchIService.search(ms008001SearchRequest, companyID);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS008001_SEARCH_EMPLOYEE_STATUS, ms008001SearchRequest, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }

}
