package com.brycen.hrm.masterservice.ms016001Search;

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
 * [Description]: Control request and response data of EvaluateProject<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS016001SearchController {
    /**
     * Call MS016001SearchService to use action search evaluate project
     */
    @Autowired
    private MS016001SearchService searchService;

    /**
     * Call LoggerService to write log
     */
    @Autowired
    LoggerService logger;

    /**
     * [Description]:Find all EvaluateProject what pass condition<br/>
     * [ Remarks ]:<br/>
     *
     * @param MS016001SearchRequest
     * @param HttpServletRequest
     * @return Response data and status Ok if true
     */
    @PostMapping(value = UrlAPI.MS016001_SEARCH_EVALUATE_PROJECT)
    public ResponseEntity<?> ms016001SearchEvaluateProject(@RequestBody MS016001SearchRequest searchRequest, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS016001_SEARCH_EVALUATE_PROJECT, searchRequest, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = searchService.search(searchRequest, companyID);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS016001_SEARCH_EVALUATE_PROJECT, searchRequest, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }
}
