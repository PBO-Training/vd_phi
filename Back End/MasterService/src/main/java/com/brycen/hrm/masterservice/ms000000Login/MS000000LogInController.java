package com.brycen.hrm.masterservice.ms000000Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.constant.LogValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.logger.ILogger;
import com.brycen.hrm.logger.LogLevel;

@RestController
public class MS000000LogInController {

    @Autowired
    MS000000ILoginService loginService;

    @Autowired
    ILogger iLogger;

    /**
     * [Description]:Check info login of user <br/>
     * [ Remarks ]:<br/>
     *
     * @param loginRequest
     * @return MS000000LogInResponse
     */
    @PostMapping(value = "/auth/" + UrlAPI.MS000000_LOGIN)
    public ResponseEntity<?> ms000000Login(@RequestBody MS000000LogInRequest loginRequest) {
        iLogger.write(LogLevel.INFOMATION, UrlAPI.MS000000_LOGIN, loginRequest, null, LogValue.BEGIN_API);
        BaseResponse baseReponse = loginService.login(loginRequest);
        HttpStatus httpStatus = HttpStatus.OK;
        if (baseReponse.getError() != null) {
            httpStatus = HttpStatus.BAD_REQUEST;
            iLogger.write(LogLevel.ERROR, UrlAPI.MS000000_LOGIN, loginRequest, baseReponse, "");
        }
        iLogger.write(LogLevel.INFOMATION, UrlAPI.MS000000_LOGIN, loginRequest, baseReponse, LogValue.END_API);
        return new ResponseEntity<>(baseReponse, httpStatus);
    }
}