package com.brycen.hrm.masterservice.ms004002Update;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.constant.LogValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.LevelSkillEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Update Controller for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@CrossOrigin
@RestController
public class MS004002UpdateController {

    /**
     * Call service layer to update skill level
     */
    @Autowired
    private MS004002UpdateIService ms004002UpdateIService;

    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Update a skill level<br/>
     * [ Remarks ]:<br/>
     *
     * @param levelSkillEntity
     * @param servletReq
     * @return Response OK status if success
     */
    @PutMapping(value = UrlAPI.MS004002_UPDATE_SKILL_LEVEL)
    public ResponseEntity<?> ms004002UpdateSkillLevel(@RequestBody LevelSkillEntity levelSkillEntity, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS004002_UPDATE_SKILL_LEVEL, levelSkillEntity, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = ms004002UpdateIService.save(levelSkillEntity, companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS004002_UPDATE_SKILL_LEVEL, levelSkillEntity, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }

}
