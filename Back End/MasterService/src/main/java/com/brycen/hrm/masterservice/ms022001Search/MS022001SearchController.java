package com.brycen.hrm.masterservice.ms022001Search;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.constant.LogValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]:Controller request and response for search skill project<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Controller
public class MS022001SearchController {
    /**
     * Call service to search languages category
     */
    @Autowired
    private MS022001SearchService searchService;

    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Find all languages category what pass condition<br/>
     * [ Remarks ]:<br/>
     *
     * @param searchRequest
     * @return Response data and status Ok if true
     */
    @PostMapping(value = UrlAPI.MS022001_SEARCH_SKILL_PROJECT)
    public ResponseEntity<?> ms022001searchSkillProject(@RequestBody MS022001SearchRequest searchRequest, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS022001_SEARCH_SKILL_PROJECT, searchRequest, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = searchService.search(searchRequest, companyID);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS022001_SEARCH_SKILL_PROJECT, searchRequest, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }
}
