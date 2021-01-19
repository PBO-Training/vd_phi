package com.brycen.hrm.masterservice.ms006002GetDetail;

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
import com.brycen.hrm.entity.LevelLanguageEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

@CrossOrigin
@RestController
public class MS006002GetDetailController {
    /**
     * Call to Service and get methods to do actions Get detail level language
     */
    @Autowired
    private MS006002GetDetailService getDetailService;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Receive request by param in RequestBody and return level language is specification and status to client<br/>
     * [ Remarks ]:<br/>
     *
     * @param levelLanguageEntity
     * @return level language specification and status OK if true
     */
    @PostMapping(value = UrlAPI.MS006002_GETDETAIL_LEVEL_LANGUAGE)
    public ResponseEntity<?> ms006002DetailLanguage(@RequestBody LevelLanguageEntity levelLanguageEntity, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS006002_GETDETAIL_LEVEL_LANGUAGE, levelLanguageEntity, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = getDetailService.getDetail(levelLanguageEntity.getLevelLanguageID(), companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS006002_GETDETAIL_LEVEL_LANGUAGE, levelLanguageEntity, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }
}
