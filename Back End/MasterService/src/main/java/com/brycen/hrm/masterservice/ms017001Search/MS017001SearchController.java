package com.brycen.hrm.masterservice.ms017001Search;

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
public class MS017001SearchController {

    @Autowired
    MS017001SearchService ms017001SearchService;

    @Autowired
    private LoggerService logger;

    @PostMapping(value = UrlAPI.MS017001_SEARCH_ROLE_SCREEN)
    public ResponseEntity<?> ms017001searchRoleScreen(@RequestBody MS017001SearchRequest ms017001SearchRequets, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS017001_SEARCH_ROLE_SCREEN, ms017001SearchRequets, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        HttpStatus httpStatus = HttpStatus.OK;
        BaseResponse baseRes = ms017001SearchService.search(ms017001SearchRequets, companyID);
        if (baseRes.getError() != null) {
            httpStatus = HttpStatus.BAD_REQUEST;
            logger.write(LogLevel.ERROR, UrlAPI.MS017001_SEARCH_ROLE_SCREEN, ms017001SearchRequets, baseRes, "");
        }
        logger.write(LogLevel.INFOMATION, UrlAPI.MS017001_SEARCH_ROLE_SCREEN, ms017001SearchRequets, baseRes, LogValue.BEGIN_API);
        return new ResponseEntity<>(baseRes, httpStatus);
    }
}
