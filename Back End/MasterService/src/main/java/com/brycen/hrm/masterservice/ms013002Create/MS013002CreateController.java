package com.brycen.hrm.masterservice.ms013002Create;

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
 * [Description]: Create Controller for Holiday Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS013002CreateController {

    /**
     * Call service to create a new holiday
     */
    @Autowired
    private MS013002CreateService createSerivce;

    /**
     * Write log
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Create a new holiday<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms013CreateRequest
     * @return Content and error status
     */
    @PostMapping(value = UrlAPI.MS013002_CREATE_HOLIDAY)
    public ResponseEntity<?> ms013002CreateHoliday(@RequestBody MS013002CreateRequest ms013CreateRequest, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS013002_CREATE_HOLIDAY, ms013CreateRequest, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = createSerivce.save(ms013CreateRequest, companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS013002_CREATE_HOLIDAY, ms013CreateRequest, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }

}
