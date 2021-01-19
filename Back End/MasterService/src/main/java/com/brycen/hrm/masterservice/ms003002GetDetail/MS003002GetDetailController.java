package com.brycen.hrm.masterservice.ms003002GetDetail;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.constant.LogValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.SkillEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

@CrossOrigin
@RestController
public class MS003002GetDetailController {
    @Autowired
    private MS003002GetDetailService getDetailService;

    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Receive request by param in RequestBody and return skill is specification and status to client<br/>
     * [ Remarks ]:<br/>
     *
     * @param skillEntity
     * @return skill specification and status OK if true
     */
    @PostMapping(value = UrlAPI.MS003002_GETDETAIL_SKILL)
    public ResponseEntity<?> ms003002DetailSkill(@RequestBody SkillEntity skillEntity, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS003002_GETDETAIL_SKILL, skillEntity, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = getDetailService.getDetail(skillEntity.getSkillID(), companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS003002_GETDETAIL_SKILL, skillEntity, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }
}
