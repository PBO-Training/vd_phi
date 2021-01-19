package com.brycen.hrm.masterservice.ms020001Delete;

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
 * [Description]: Delete Controller for User Master Degree Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@RestController
@CrossOrigin
public class MS020001DeleteController {
    /**
     * Call service layer to delete Degree
     */
    @Autowired
    MS020001DeleteIService ms020001deleteIService;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Delete multiple Degree<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms012001DeleteRequest
     * @return Response OK status if success
     */
    @DeleteMapping(value = UrlAPI.MS020001_DELETE_DEGREE)
    public ResponseEntity<?> deleteListVacationType(@RequestBody MS020001DeleteRequest request, HttpServletRequest servletReq) {
        BaseResponse baseRes = new BaseResponse();
        logger.write(LogLevel.INFOMATION, UrlAPI.MS020001_DELETE_DEGREE, request, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        int result = ms020001deleteIService.delete(request.getListDelete(), companyID);
        baseRes.setContent(result);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS020001_DELETE_DEGREE, request, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }
}
