package com.brycen.hrm.masterservice.ms015001Search;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.constant.LogValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

@CrossOrigin
@RestController
public class MS015001SearchController {
    @Autowired
    private MS015001SearchService searchService;

    @Autowired
    LoggerService logger;

    /**
     * [Description]: Find all contractType what pass condition<br/>
     * [ Remarks ]:<br/>
     *
     * @param servletReq
     * @return Response data and status Ok if true
     */
    @PostMapping(value = UrlAPI.MS015001_SEARCH_CONTRACTTYPE)
    public ResponseEntity<?> ms009001SearchContractType(HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS015001_SEARCH_CONTRACTTYPE, null, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = searchService.search(companyID);
        logger.write(LogLevel.INFOMATION, UrlAPI.MS015001_SEARCH_CONTRACTTYPE, null, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, HttpStatus.OK);
    }
}
