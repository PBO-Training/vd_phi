package com.brycen.hrm.masterservice.ms008002Update;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.SqlValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.StatusEmployeeEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Update Service Implementation for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS008002UpdateImpl implements MS008002UpdateIService {

    /**
     * Support find an employee status by ID
     */
    @Autowired
    private MS008002UpdateIRepository ms008002UpdateIRepository;

    /**
     * Call log service to write log for api
     */
    @Autowired
    private LoggerService logger;
    
    public ErrorResponse checkValue(StatusEmployeeEntity statusEmployeeEntity, Optional<StatusEmployeeEntity> currentStatusEmployee, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check duplicate code
        StatusEmployeeEntity statusEmployee = ms008002UpdateIRepository.findStatusEmployeeByStatusEmployeeCodeAndCompanyID(
                statusEmployeeEntity.getStatusEmployeeCode().trim(), companyID, currentStatusEmployee.get().getStatusEmployeeID());
        if (statusEmployee != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_STATUS_EMPLOYEE)
                    .append(ErrorValue.API_UPDATE_DETAIL_STATUS_EMPLOYEE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("statusEmployeeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        } else {
            // check null code
            if (CheckValueService.checkNull(statusEmployeeEntity.getStatusEmployeeCode().trim())) {
                errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_STATUS_EMPLOYEE)
                        .append(ErrorValue.API_UPDATE_DETAIL_STATUS_EMPLOYEE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
                errorItemName.append("statusEmployeeCode");
                error.setCode(errorItemCode.toString());
                error.setItemName(errorItemName.toString());
                return error;
            }
        }

        // Check name is null
        if (CheckValueService.checkNull(statusEmployeeEntity.getStatusEmployeeName().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_STATUS_EMPLOYEE)
                    .append(ErrorValue.API_UPDATE_DETAIL_STATUS_EMPLOYEE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("statusEmployeeName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        if (CheckValueService.checkMaxLength(statusEmployeeEntity.getStatusEmployeeCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(statusEmployeeEntity.getStatusEmployeeName().trim(), SqlValue.LENGTH_STRING)) {

            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_STATUS_EMPLOYEE)
                    .append(ErrorValue.API_UPDATE_DETAIL_STATUS_EMPLOYEE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

        }
        if (CheckValueService.checkMaxLength(statusEmployeeEntity.getStatusEmployeeCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("statusEmployeeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(statusEmployeeEntity.getStatusEmployeeName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("statusEmployeeName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (!currentStatusEmployee.isPresent()) {
            errorItemName.append("statusEmployeeID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_STATUS_EMPLOYEE)
                    .append(ErrorValue.API_UPDATE_DETAIL_STATUS_EMPLOYEE).append(ErrorValue.METHOD_PUT).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(statusEmployeeEntity.getStatusEmployeeCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_STATUS_EMPLOYEE)
            .append(ErrorValue.API_UPDATE_DETAIL_STATUS_EMPLOYEE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("statusEmployeeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse save(StatusEmployeeEntity statusEmployeeEntity, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<StatusEmployeeEntity> currentStatusEmployee = ms008002UpdateIRepository
                .findByStatusEmployeeIDAndCompanyIDAndIsDelete(statusEmployeeEntity.getStatusEmployeeID(), companyID, false);
        ErrorResponse error = checkValue(statusEmployeeEntity, currentStatusEmployee, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS008002_UPDATE_EMPLOYEE_STATUS, statusEmployeeEntity, baseRes, "");
            return baseRes;
        }
        String statusEntityCode = statusEmployeeEntity.getStatusEmployeeCode().trim().toUpperCase();
        StatusEmployeeEntity statusEmployee = currentStatusEmployee.get();
        statusEmployee.setIsDelete(statusEmployeeEntity.getIsDelete());
        statusEmployee.setStatusEmployeeName(statusEmployeeEntity.getStatusEmployeeName().trim());
        statusEmployee.setStatusEmployeeCode(statusEntityCode);
        ms008002UpdateIRepository.save(statusEmployee);
        return baseRes;
    }

}
