package com.brycen.hrm.masterservice.ms019001Search;

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
 * [Description]: Search Controller for Scope Work Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS019001SearchController {

    /**
     * Call service to search a list scope work
     */
    @Autowired
    private MS019001SearchIService searchService;

    /**
     * Write log
     */
    @Autowired
    LoggerService logger;

    /**
     * [Description]: Seach a list scope work<br/>
     * [ Remarks ]:<br/>
     *
     * @param searchRequest
     * @param servletReq
     * @return List scope work
     */
    @PostMapping(value = UrlAPI.MS019001_SEARCH_SCOPEWORK)
    public ResponseEntity<?> ms019001SearchScopeWork(@RequestBody MS019001SearchRequest searchRequest, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS019001_SEARCH_SCOPEWORK, searchRequest, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = searchService.search(searchRequest, companyID);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS019001_SEARCH_SCOPEWORK, searchRequest, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }

}
