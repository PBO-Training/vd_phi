package com.brycen.hrm.masterservice.ms017002GetDetail;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.constant.LogValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

@RestController
public class MS017002GetDetailController {
    @Autowired
    MS017002GetDetailService ms017002GetDetailService;

    @Autowired
    private LoggerService logger;

    @PostMapping(value = UrlAPI.MS017002_GETDETAIL_ROLE_SCREEN)
    public ResponseEntity<?> ms017002getDetail(@RequestBody MS017002GetDetailRequest ms017002GetDetailRequest, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS017002_GETDETAIL_ROLE_SCREEN, ms017002GetDetailRequest, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        HttpStatus httpStatus = HttpStatus.OK;
        BaseResponse baseRes = ms017002GetDetailService.getDetail(ms017002GetDetailRequest, companyID);
        if (baseRes.getError() != null) {
            httpStatus = HttpStatus.BAD_REQUEST;
            logger.write(LogLevel.ERROR, UrlAPI.MS017002_GETDETAIL_ROLE_SCREEN, ms017002GetDetailRequest, baseRes, "");
        }
        logger.write(LogLevel.INFOMATION, UrlAPI.MS017002_GETDETAIL_ROLE_SCREEN, ms017002GetDetailRequest, baseRes, LogValue.BEGIN_API);
        return new ResponseEntity<>(baseRes, httpStatus);
    }
}
