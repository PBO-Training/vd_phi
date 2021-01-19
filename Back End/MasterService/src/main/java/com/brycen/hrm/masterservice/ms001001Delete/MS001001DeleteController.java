package com.brycen.hrm.masterservice.ms001001Delete;

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
 * [Description]: Delete Controller for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@RestController
@CrossOrigin
public class MS001001DeleteController {

    /**
     * Call service layer to delete user
     */
    @Autowired
    MS001001DeleteIService ms001001DeleteIService;

    /**
     * Call log service to write log for api
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Delete multiple users<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms001001DeleteRequest
     * @return Response OK status if success
     */
    @DeleteMapping(value = UrlAPI.MS001001_DELETE_USER)
    public ResponseEntity<?> deleteListUser(@RequestBody MS001001DeleteRequest ms001001DeleteRequest, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS001001_DELETE_USER, ms001001DeleteRequest, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        int res = ms001001DeleteIService.deleteListUser(ms001001DeleteRequest.getListDelete(), companyID);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(res);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS001001_DELETE_USER, ms001001DeleteRequest, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }

}
