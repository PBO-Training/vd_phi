package com.brycen.hrm.masterservice.ms018002Update;

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
import com.brycen.hrm.entity.RoleEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Update Controller for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS018002UpdateController {

    /**
     * Call service to update a role
     */
    @Autowired
    private MS018002UpdateIService ms018002UpdateIService;

    /**
     * Write log
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Update a role<br/>
     * [ Remarks ]:<br/>
     *
     * @param roleEntity
     * @param servletReq
     * @return Content and error status
     */
    @PutMapping(value = UrlAPI.MS018002_UPDATE_GROUP_ROLE)
    public ResponseEntity<?> ms018002UpdateRole(@RequestBody RoleEntity roleEntity, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS018002_UPDATE_GROUP_ROLE, roleEntity, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = ms018002UpdateIService.save(roleEntity, companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS018002_UPDATE_GROUP_ROLE, roleEntity, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }

}
