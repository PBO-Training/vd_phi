package com.brycen.hrm.masterservice.ms001001Init;

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
 * [Description]: Init Controller for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@RestController
@CrossOrigin
public class MS001001InitController {

    /**
     * Support write log
     */
    @Autowired
    private LoggerService logger;

    /**
     * Call service to support initialize
     */
    @Autowired
    private MS001001InitIService ms001001InitIService;

    /**
     * [Description]: Initialize data for drop-down list in User<br/>
     * [ Remarks ]:<br/>
     *
     * @param req
     * @return Content and error status
     */
    @PostMapping(value = UrlAPI.MS001001_INIT_USER)
    public ResponseEntity<?> ms001001Init(HttpServletRequest req) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS001001_INIT_USER, null, null, LogValue.BEGIN_API);
        int companyID = (int) req.getAttribute("companyID");
        MS001001InitResponse init = ms001001InitIService.init(companyID, req);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setContent(init);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS001001_INIT_USER, null, baseResponse, LogValue.END_API);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
