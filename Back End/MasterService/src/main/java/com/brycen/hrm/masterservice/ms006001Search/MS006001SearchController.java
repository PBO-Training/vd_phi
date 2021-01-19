package com.brycen.hrm.masterservice.ms006001Search;

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
public class MS006001SearchController {
    @Autowired
    private MS006001SearchService searchService;

    @Autowired
    LoggerService logger;

    /**
     * [Description]: Find all levelLanguage what pass condition<br/>
     * [ Remarks ]:<br/>
     *
     * @param searchRequest
     * @param servletReq
     * @return Response data and status Ok if true
     */
    @PostMapping(value = UrlAPI.MS006001_SEARCH_LEVEL_LANGUAGE)
    public ResponseEntity<?> ms006001SearchLevelLanguage(@RequestBody MS006001SearchRequest searchRequest, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS006001_SEARCH_LEVEL_LANGUAGE, null, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = searchService.search(searchRequest, companyID);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS006001_SEARCH_LEVEL_LANGUAGE, null, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }
}
