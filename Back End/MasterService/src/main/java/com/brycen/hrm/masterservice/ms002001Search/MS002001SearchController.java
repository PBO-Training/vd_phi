package com.brycen.hrm.masterservice.ms002001Search;

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
 * [Description]:<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS002001SearchController {
    /**
     * Call service to search departments
     */
    @Autowired
    private MS002001SearchService searchService;

    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Find all department what pass condition<br/>
     * [ Remarks ]:<br/>
     *
     * @param searchRequest
     * @return Response data and status Ok if true
     */
    @PostMapping(value = UrlAPI.MS002001_SEARCH_DEPARTMENT)
    public ResponseEntity<?> ms002002searchDepartment(@RequestBody MS002001SearchRequest searchRequest, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS002001_SEARCH_DEPARTMENT, searchRequest, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = searchService.search(searchRequest, companyID);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS002001_SEARCH_DEPARTMENT, searchRequest, baseRes, LogValue.BEGIN_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }
}
