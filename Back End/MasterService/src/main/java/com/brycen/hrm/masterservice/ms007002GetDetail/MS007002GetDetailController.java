package com.brycen.hrm.masterservice.ms007002GetDetail;

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
import com.brycen.hrm.entity.PositionEmployeeEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

@CrossOrigin
@RestController
public class MS007002GetDetailController {
    @Autowired
    private MS007002GetDetailService getDetailService;

    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Receive request by param in RequestBody and return employee position is specification and status to client<br/>
     * [ Remarks ]:<br/>
     *
     * @param po
     * @return employee position specification and status OK if true
     */
    @PostMapping(value = UrlAPI.MS007002_GETDETAIL_EMPLOYEE_POSITION)
    public ResponseEntity<?> ms007002DetailPosition(@RequestBody PositionEmployeeEntity positionEmployeeEntity, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS007002_GETDETAIL_EMPLOYEE_POSITION, positionEmployeeEntity, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = getDetailService.getDetail(positionEmployeeEntity.getPositionEmployeeID(), companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS007002_GETDETAIL_EMPLOYEE_POSITION, positionEmployeeEntity, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }
}
