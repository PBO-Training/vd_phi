package com.brycen.hrm.masterservice.ms018001Delete;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.constant.LogValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Delete Controller for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@RestController
@CrossOrigin
public class MS018001DeleteController {

    /**
     * Call service to delete a list role
     */
    @Autowired
    MS018001DeleteIService deleteIService;

    /**
     * Write log
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Delete a list role<br/>
     * [ Remarks ]:<br/>
     *
     * @param request
     * @param servletReq
     * @return Content and error status
     */
    @DeleteMapping(value = UrlAPI.MS018001_DELETE_GROUP_ROLE)
    public ResponseEntity<?> deleteListRole(@RequestBody MS018001DeleteRequest request, HttpServletRequest servletReq) {
        BaseResponse baseRes = new BaseResponse();
        logger.write(LogLevel.INFOMATION, UrlAPI.MS018001_DELETE_GROUP_ROLE, request, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        baseRes = deleteIService.delete(request.getListDelete(), companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS018001_DELETE_GROUP_ROLE, request, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }

}
