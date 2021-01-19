package com.brycen.hrm.masterservice.ms012002Update;

import java.util.Optional;

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
 * [Description]: This is place to update vacation type<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS012002UpdateImpl implements MS012002UpdateService {
    /**
     * Call to Repository and get methods to do actions update vacation type
     */
    @Autowired
    private MS012002UpdateRepository updateRepository;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    LoggerService logger;

    public ErrorResponse checkValue(VacationTypeEntity vacationTypeEntity, Optional<VacationTypeEntity> currentvacationType, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check duplicate code
        VacationTypeEntity vacationType = updateRepository.findVacationTypeByVacationTypeCodeAndCompanyID(vacationTypeEntity.getVacationTypeCode().trim(), companyID,
                currentvacationType.get().getVacationTypeID());
        if (vacationType != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_TYPE_VACATION)
                    .append(ErrorValue.API_UPDATE_DETAIL_VACATION).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("vacationTypeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        } else {
            // check null code
            if (CheckValueService.checkNull(vacationTypeEntity.getVacationTypeCode().trim())) {
                errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_TYPE_VACATION)
                        .append(ErrorValue.API_UPDATE_DETAIL_VACATION).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
                errorItemName.append("vacationTypeCode");
                error.setCode(errorItemCode.toString());
                error.setItemName(errorItemName.toString());
                return error;
            }
        }

        // Check name is null
        if (CheckValueService.checkNull(vacationTypeEntity.getVacationTypeName().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_TYPE_VACATION)
                    .append(ErrorValue.API_UPDATE_DETAIL_VACATION).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("vacationTypeName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        
        if (CheckValueService.checkMaxLength(vacationTypeEntity.getVacationTypeCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(vacationTypeEntity.getVacationTypeDescription().trim(), SqlValue.LENGTH_DESCRIPTION)
                || CheckValueService.checkMaxLength(vacationTypeEntity.getVacationTypeName().trim(), SqlValue.LENGTH_STRING)) {

            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_TYPE_VACATION)
                    .append(ErrorValue.API_UPDATE_DETAIL_VACATION).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

        }
        if (CheckValueService.checkMaxLength(vacationTypeEntity.getVacationTypeCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("vacationTypeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(vacationTypeEntity.getVacationTypeDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemName.append("vacationTypeDescription");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(vacationTypeEntity.getVacationTypeName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("vacationTypeName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (!currentvacationType.isPresent()) {
            errorItemName.append("vacationTypeID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_TYPE_VACATION)
                    .append(ErrorValue.API_UPDATE_DETAIL_VACATION).append(ErrorValue.METHOD_PUT).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(vacationTypeEntity.getVacationTypeCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_TYPE_VACATION)
            .append(ErrorValue.API_UPDATE_DETAIL_VACATION).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("vacationTypeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    /**
     * [Description]: Method find a vacation type with id specification and update it<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return BaseResponse - Model contain data what need to send to client
     */
    @Override
    public BaseResponse save(VacationTypeEntity vacationTypeEntity, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<VacationTypeEntity> currentvacationType = updateRepository.findByVacationTypeIDAndCompanyIDAndIsDelete(vacationTypeEntity.getVacationTypeID(),
                companyID, false);
        ErrorResponse error = checkValue(vacationTypeEntity, currentvacationType, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS012002_UPDATE_VACATION_TYPE, vacationTypeEntity, baseRes, "");
            return baseRes;
        }
        String vacationTypeCode = vacationTypeEntity.getVacationTypeCode().trim().toUpperCase();
        VacationTypeEntity pos = currentvacationType.get();
        pos.setVacationTypeName(vacationTypeEntity.getVacationTypeName().trim());
        pos.setIsDelete(vacationTypeEntity.getIsDelete());
        pos.setVacationTypeCode(vacationTypeCode);
        pos.setVacationTypeDescription(vacationTypeEntity.getVacationTypeDescription().trim());
        updateRepository.save(pos);
        return baseRes;
    }
}
