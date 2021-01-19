package com.brycen.hrm.masterservice.ms019002Update;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.constant.LogValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.ScopeWorkEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Update Controller for Scope Work Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS019002UpdateController {

    /**
     * Call service to update a Scope Work
     */
    @Autowired
    private MS019002UpdateIService ms019002UpdateIService;

    /**
     * Write log
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Update a Scope Work<br/>
     * [ Remarks ]:<br/>
     *
     * @param Scope Work Entity
     * @param servletReq
     * @return Content and error status
     */
    @PutMapping(value = UrlAPI.MS019002_UPDATE_SCOPEWORK)
    public ResponseEntity<?> ms019002UpdateScopeWork(@RequestBody ScopeWorkEntity scopeWorkEntity, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS019002_UPDATE_SCOPEWORK, scopeWorkEntity, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = ms019002UpdateIService.save(scopeWorkEntity, companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS019002_UPDATE_SCOPEWORK, scopeWorkEntity, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }

}
