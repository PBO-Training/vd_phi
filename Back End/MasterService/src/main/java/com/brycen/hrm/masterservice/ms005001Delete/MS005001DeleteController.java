package com.brycen.hrm.masterservice.ms005001Delete;

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
 * [Description]:Delete Controller for Master language Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */

@RestController
@CrossOrigin
public class MS005001DeleteController {
    /**
     * Call service layer to delete language
     */
    @Autowired
    MS005001DeleteService ms005001DeleteService;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Delete multiple language<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms005001DeleteRequest
     * @return Response OK status if success
     */
    @DeleteMapping(value = UrlAPI.MS005001_DELETE_LANGUAGE)
    public ResponseEntity<?> deleteListLanguage(@RequestBody MS005001DeleteRequest request, HttpServletRequest servletReq) {
        BaseResponse baseRes = new BaseResponse();
        logger.write(LogLevel.INFOMATION, UrlAPI.MS005001_DELETE_LANGUAGE, request, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        int result = ms005001DeleteService.delete(request.getListDelete(), companyID);
        baseRes.setContent(result);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS005001_DELETE_LANGUAGE, request, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }
}
