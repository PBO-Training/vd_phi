package com.brycen.hrm.masterservice.ms019002GetDetail;

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
import com.brycen.hrm.entity.ScopeWorkEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Get Details Controller for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS019002GetDetailController {

    /**
     * Call service to find a role
     */
    @Autowired
    private MS019002GetDetailIService ms019002GetDetailIService;

    /**
     * Write log
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Get a role details<br/>
     * [ Remarks ]:<br/>
     *
     * @param roleEntity
     * @param servletReq
     * @return Content and error status
     */
    @PostMapping(value = UrlAPI.MS019002_GETDETAIL_SCOPEWORK)
    public ResponseEntity<?> ms019002DetailScopeWork(@RequestBody ScopeWorkEntity scopeWorkEntity, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS019002_GETDETAIL_SCOPEWORK, scopeWorkEntity, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = ms019002GetDetailIService.getDetail(scopeWorkEntity.getScopeWorkID(), companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS019002_GETDETAIL_SCOPEWORK, scopeWorkEntity, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }

}
