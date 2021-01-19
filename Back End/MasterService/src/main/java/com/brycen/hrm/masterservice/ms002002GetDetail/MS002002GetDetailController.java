package com.brycen.hrm.masterservice.ms002002GetDetail;

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
import com.brycen.hrm.entity.DepartmentEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

@CrossOrigin
@RestController
public class MS002002GetDetailController {
    @Autowired
    private MS002002GetDetailService getDetailService;

    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Receive request by param in RequestBody and return department is specification and status to client<br/>
     * [ Remarks ]:<br/>
     *
     * @param departmentEntity
     * @return department specification and status OK if true
     */
    @PostMapping(value = UrlAPI.MS002002_GETDETAIL_DEPARTMENT)
    public ResponseEntity<?> ms002002DetailDepartment(@RequestBody DepartmentEntity departmentEntity, HttpServletRequest servletReq) {
        logger.write(LogLevel.INFOMATION, UrlAPI.MS002002_GETDETAIL_DEPARTMENT, departmentEntity, null, LogValue.BEGIN_API);
        int companyID = (int) servletReq.getAttribute("companyID");
        BaseResponse baseRes = getDetailService.getDetail(departmentEntity.getDepartmentID(), companyID);
        HttpStatus status = baseRes.getError() == null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        logger.write(LogLevel.INFOMATION, UrlAPI.MS002002_GETDETAIL_DEPARTMENT, departmentEntity, baseRes, LogValue.END_API);
        return new ResponseEntity<>(baseRes, status);
    }
}
