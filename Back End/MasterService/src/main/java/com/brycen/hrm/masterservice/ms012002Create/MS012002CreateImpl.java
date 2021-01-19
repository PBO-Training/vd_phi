package com.brycen.hrm.masterservice.ms012002Create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.SqlValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.VacationTypeEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: This is place make to create new vacation type<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS012002CreateImpl implements MS012002CreateService {
    /**
     * Call to Repository and get methods to do actions Create new vacation type
     */
    @Autowired
    private MS012002CreateRepository createRepository;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    LoggerService logger;

    public ErrorResponse checkValue(VacationTypeEntity vacationTypeEntity, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check vacation type code is null
        if (CheckValueService.checkNull(vacationTypeEntity.getVacationTypeCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_TYPE_VACATION)
                    .append(ErrorValue.API_CREATE_DETAIL_VACATION).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("vacationTypeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check vacation type name is null
        if (CheckValueService.checkNull(vacationTypeEntity.getVacationTypeName().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_TYPE_VACATION)
                    .append(ErrorValue.API_CREATE_DETAIL_VACATION).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("vacationTypeName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check vacation type code is duplicate
        VacationTypeEntity vacationType = createRepository.findVacationTypeByVacationTypeCodeAndCompanyID(vacationTypeEntity.getVacationTypeCode().trim(), companyID);
        if (vacationType != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_TYPE_VACATION)
                    .append(ErrorValue.API_CREATE_DETAIL_VACATION).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("vacationTypeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If vacationTypeCode or vacationTypeName is length illegal then set value to errorItemCode
        if (CheckValueService.checkMaxLength(vacationTypeEntity.getVacationTypeCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(vacationTypeEntity.getVacationTypeDescription().trim(), SqlValue.LENGTH_DESCRIPTION)
                || CheckValueService.checkMaxLength(vacationTypeEntity.getVacationTypeName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_TYPE_VACATION)
                    .append(ErrorValue.API_CREATE_DETAIL_VACATION).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
        }

        // If vacationTypeCode is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(vacationTypeEntity.getVacationTypeCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("vacationTypeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If vacationTypeDescription is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(vacationTypeEntity.getVacationTypeDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemName.append("vacationTypeDescription");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If vacationTypeName is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(vacationTypeEntity.getVacationTypeName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("vacationTypeName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(vacationTypeEntity.getVacationTypeCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_TYPE_VACATION)
            .append(ErrorValue.API_CREATE_DETAIL_VACATION).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("vacationTypeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse save(VacationTypeEntity vacationTypeEntity, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        ErrorResponse error = checkValue(vacationTypeEntity, companyID);

        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS012002_CREATE_VACATION_TYPE, vacationTypeEntity, baseRes, "");
            return baseRes;
        }

        String vacationTypeCode = vacationTypeEntity.getVacationTypeCode().trim().toUpperCase();
        vacationTypeEntity.setCompanyID(companyID);
        vacationTypeEntity.setVacationTypeCode(vacationTypeCode);
        String vacationTypeName = vacationTypeEntity.getVacationTypeName().trim();
        vacationTypeEntity.setVacationTypeName(vacationTypeName);
        String vacationTypeDescription = vacationTypeEntity.getVacationTypeDescription().trim();
        vacationTypeEntity.setVacationTypeDescription(vacationTypeDescription);
        createRepository.save(vacationTypeEntity);
        return baseRes;
    }
}
