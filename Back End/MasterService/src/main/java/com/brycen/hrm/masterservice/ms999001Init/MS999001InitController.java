package com.brycen.hrm.masterservice.ms999001Init;

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
 * [Description]:Class controll request and response for dashboard<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS999001InitController {
    /**
     * Call service to get statistic dashboard
     */
    @Autowired
    private MS999001InitService initDashboardService;

    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Method controll request and response<br/>
     * [ Remarks ]:<br/>
     *
     * @param HttpServletRequest
     * @return Response data and status Ok if true
     */
    @PostMapping(value = UrlAPI.MS999001_INIT_TOTAL_EMPLOYEE)
    public ResponseEntity<?> ms999001InitDashboard(HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS999001_INIT_TOTAL_EMPLOYEE, null, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = initDashboardService.getTotalEmployee(companyID);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS999001_INIT_TOTAL_EMPLOYEE, null, baseRes, LogValue.BEGIN_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }
}
