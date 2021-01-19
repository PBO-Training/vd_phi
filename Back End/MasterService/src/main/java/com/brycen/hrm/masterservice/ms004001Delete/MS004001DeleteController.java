package com.brycen.hrm.masterservice.ms004001Delete;

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
 * [Description]: Delete Controller for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@RestController
@CrossOrigin
public class MS004001DeleteController {

    /**
     * Call service layer to delete skill level
     */
    @Autowired
    MS004001DeleteIService ms004001DeleteIService;

    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Delete multiple skill levels<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms004001DeleteRequest
     * @param servletReq
     * @return Response OK status if success
     */
    @DeleteMapping(value = UrlAPI.MS004001_DELETE_SKILL_LEVEL)
    public ResponseEntity<?> deleteListSkillLevel(@RequestBody MS004001DeleteRequest ms004001DeleteRequest, HttpServletRequest servletReq) {
        BaseResponse baseRes = new BaseResponse();
        logger.write(LogLevel.INFOMATION, UrlAPI.MS004001_DELETE_SKILL_LEVEL, ms004001DeleteRequest, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        int result = ms004001DeleteIService.delete(ms004001DeleteRequest.getListDelete(), companyID);
        baseRes.setContent(result);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS004001_DELETE_SKILL_LEVEL, ms004001DeleteRequest, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }

}
