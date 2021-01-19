package com.brycen.hrm.masterservice.ms017002Update;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.constant.LogValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

@RestController
public class MS017002UpdateController {

    @Autowired
    private MS017002UpdateService ms017002UpdateService;

    @Autowired
    private LoggerService logger;

    @PutMapping(value = UrlAPI.MS017002_UPDATE_ROLE_SCREEN)
    public ResponseEntity<?> ms017001init(@RequestBody List<MS017002UpdateRequest> listMs017002UpdateRequest, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS017002_UPDATE_ROLE_SCREEN, null, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        HttpStatus httpStatus = HttpStatus.OK;
        BaseResponse baseRes = ms017002UpdateService.update(listMs017002UpdateRequest, companyID);
        if (baseRes.getError() != null) {
            httpStatus = HttpStatus.BAD_REQUEST;
            logger.write(LogLevel.ERROR, UrlAPI.MS017002_UPDATE_ROLE_SCREEN, null, baseRes, "");
        }
        logger.write(LogLevel.INFOMATION, UrlAPI.MS017002_UPDATE_ROLE_SCREEN, null, baseRes, LogValue.BEGIN_API);
        return new ResponseEntity<>(baseRes, httpStatus);
    }
}
