package com.brycen.hrm.masterservice.ms008002GetDetail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.StatusEmployeeEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Get Details Service Implementation for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS008002GetDetailImpl implements MS008002GetDetailIService {

    /**
     * Support find an employee status by ID
     */
    @Autowired
    private MS008002GetDetailIRepository ms008002GetDetailIRepository;

    /**
     * Call log service to write log for api
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]: Check Employee Status is unique<br/>
     * [ Remarks ]:<br/>
     *
     * @param employeeStatusID
     * @param currentEmployeeStatus
     * @return null if true and error status if false
     */
    public ErrorResponse validation(long employeeStatusID, Optional<StatusEmployeeEntity> currentEmployeeStatus) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        if (!currentEmployeeStatus.isPresent()) {
            errorItemName.append("employeeStatusID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_STATUS_EMPLOYEE)
                    .append(ErrorValue.API_SEARCH_LIST_STATUS_EMPLOYEE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse getDetail(long statusEmployeeID, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<StatusEmployeeEntity> currentStatusEmployee = ms008002GetDetailIRepository.findByStatusEmployeeIDAndCompanyIDAndIsDelete(statusEmployeeID,
                companyID, false);
        ErrorResponse error = validation(statusEmployeeID, currentStatusEmployee);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS008002_GETDETAIL_EMPLOYEE_STATUS, statusEmployeeID, baseRes, "");
            return baseRes;
        }
        StatusEmployeeEntity statusEmployee = currentStatusEmployee.get();
        MS008002GetDetailResponse searchOneRes = new MS008002GetDetailResponse();
        searchOneRes.setStatusEmployeeID(statusEmployee.getStatusEmployeeID());
        searchOneRes.setStatusEmployeeCode(statusEmployee.getStatusEmployeeCode());
        searchOneRes.setStatusEmployeeName(statusEmployee.getStatusEmployeeName());
        baseRes.setContent(searchOneRes);
        return baseRes;
    }

}
