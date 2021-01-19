package com.brycen.hrm.masterservice.ms001001Search;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ContentResponse;
import com.brycen.hrm.constant.LogValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Search Controller for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@RestController
@CrossOrigin
public class MS001001SearchController {

    /**
     * Call service layer to search user
     */
    @Autowired
    MS001001SearchIService ms001001SearchIService;

    /**
     * Call log service to write log for api
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Search with multiple value<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms001001SearchRequest
     * @return Response OK status if success
     */
    @PostMapping(value = UrlAPI.MS001001_SEARCH_USER)
    public ResponseEntity<?> searchUser(@RequestBody MS001001SearchRequest ms001001SearchRequest, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS001001_SEARCH_USER, ms001001SearchRequest, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        ContentResponse pageResult = ms001001SearchIService.searchUser(ms001001SearchRequest, companyID);
        BaseResponse baseRes = new BaseResponse();
        baseRes.setContent(pageResult);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS001001_SEARCH_USER, ms001001SearchRequest, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }

}
