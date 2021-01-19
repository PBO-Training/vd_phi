package com.brycen.hrm.masterservice.ms023001Search;

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
 * [Description]: Search Controller for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS023001SearchController {

    /**
     * Call service to search a list role
     */
    @Autowired
    private MS023001SearchIService searchService;

    /**
     * Write log
     */
    @Autowired
    LoggerService logger;

    /**
     * [Description]: Seach a list role<br/>
     * [ Remarks ]:<br/>
     *
     * @param searchRequest
     * @param servletReq
     * @return List role
     */
    @PostMapping(value = UrlAPI.MS023001_SEARCH_SHIFTWORKOPTION)
    public ResponseEntity<?> ms018001SearchRole(@RequestBody MS023001SearchRequest searchRequest, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS023001_SEARCH_SHIFTWORKOPTION, searchRequest, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = searchService.search(searchRequest, companyID);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS023001_SEARCH_SHIFTWORKOPTION, searchRequest, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }

}
