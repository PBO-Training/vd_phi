package com.brycen.hrm.masterservice.ms006001Delete;

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
 * [Description]: this is place send response or receive request from client<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS006001DeleteController {
    /**
     * Call to Service and get methods to do actions Delete level language
     */
    @Autowired
    MS006001DeleteService deleteService;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Delete multiple level language<br/>
     * [ Remarks ]:<br/>
     *
     * @param request
     * @param servletReq
     * @return Response OK status if success
     */
    @DeleteMapping(value = UrlAPI.MS006001_DELETE_LEVEL_LANGUAGE)
    public ResponseEntity<?> deleteListLevelLanguage(@RequestBody MS006001DeleteRequest request, HttpServletRequest servletReq) {
        BaseResponse baseRes = new BaseResponse();
        logger.write(LogLevel.INFOMATION, UrlAPI.MS006001_DELETE_LEVEL_LANGUAGE, request, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        int result = deleteService.delete(request.getListDelete(), companyID);
        baseRes.setContent(result);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS006001_DELETE_LEVEL_LANGUAGE, request, result, LogValue.END_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }
}
