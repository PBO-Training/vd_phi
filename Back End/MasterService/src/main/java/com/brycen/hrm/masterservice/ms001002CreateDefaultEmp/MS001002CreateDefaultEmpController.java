package com.brycen.hrm.masterservice.ms001002CreateDefaultEmp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.constant.LogValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.logger.ILogger;
import com.brycen.hrm.logger.LogLevel;

/**
 * [Description]:Master Service Create Default Employee Controller <br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@RestController
public class MS001002CreateDefaultEmpController {
    @Autowired
    private ILogger iLogger;

    @Autowired
    private MS001002CreateDefaultEmpService service;

    /**
     * [Description]:API Create Employee<br/>
     * [ Remarks ]:<br/>
     *
     * @param request
     * @return baseReponse
     */
    @PostMapping(value = UrlAPI.MS001002_CREATE_DEFAULT)
    public ResponseEntity<?> em001002CreateDefault(HttpServletRequest req ){
          iLogger.write(LogLevel.INFOMATION, UrlAPI.MS001002_CREATE_DEFAULT, null, null, LogValue.BEGIN_API);
          int companyID = (int) req.getAttribute("companyID");
          BaseResponse response = new BaseResponse();
          response = this.service.em001002CreateDefault(companyID);
          iLogger.write(LogLevel.INFOMATION, UrlAPI.MS001002_CREATE_DEFAULT, null, response, LogValue.END_API);
          if (response.getError() != null) {
              return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
          }
          return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}
