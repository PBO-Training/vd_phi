package com.brycen.hrm.masterservice.ms001002Init;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.constant.LogValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Initialize Controller for details screen of User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@RestController
@CrossOrigin
public class MS001002InitController {

    /**
     * Support write log
     */
    @Autowired
    private LoggerService logger;

    /**
     * Support search list employee
     */
    @Autowired
    MS001002InitIService ms001002InitIService;
   
    /**
     * [Description]: Search list employee and role<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms001002InitRequest
     * @param req
     * @return Content and error status
     */
    @PostMapping(value = UrlAPI.MS001002_INIT_USER)
    public ResponseEntity<?> ms001002Init( HttpServletRequest req) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS001002_INIT_USER, null, null, LogValue.BEGIN_API);
        int companyID = (int) req.getAttribute("companyID");
        BaseResponse baseRes = ms001002InitIService.init(companyID);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS001002_INIT_USER, null, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }

}
