package com.brycen.hrm.masterservice.ms004001Search;

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
 * [Description]: Search Controller for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS004001SearchController {

    /**
     * Call service layer to search skill level
     */
    @Autowired
    private MS004001SearchIService ms004001SearchIService;

    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Search with multiple value<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms004001SearchRequest
     * @param servletReq
     * @return Response OK status if success
     */
    @PostMapping(value = UrlAPI.MS004001_SEARCH_SKILL_LEVEL)
    public ResponseEntity<?> ms004001searchSkillLevel(@RequestBody MS004001SearchRequest ms004001SearchRequest, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS004001_SEARCH_SKILL_LEVEL, ms004001SearchRequest, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = ms004001SearchIService.search(ms004001SearchRequest, companyID);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS004001_SEARCH_SKILL_LEVEL, ms004001SearchRequest, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }

}
