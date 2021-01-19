package com.brycen.hrm.masterservice.ms007002Update;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.SqlValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.PositionEmployeeEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: This is place to update possition<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS007002UpdateImpl implements MS007002UpdateService {
    @Autowired
    private MS007002UpdateRepository updateRepository;

    @Autowired
    LoggerService logger;
    
    public ErrorResponse checkValue(PositionEmployeeEntity positionEmployeeEntity, Optional<PositionEmployeeEntity> currentPosition, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check duplicate code
        PositionEmployeeEntity positionEmployee = updateRepository.findPositionEmployeeByPositionEmployeeCodeAndCompanyID(positionEmployeeEntity.getPositionEmployeeCode().trim(), companyID,
                currentPosition.get().getPositionEmployeeID());
        if (positionEmployee != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION)
                    .append(ErrorValue.API_UPDATE_DETAIL_POSITION).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("positionEmployeeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        } else {
            // check null code
            if (CheckValueService.checkNull(positionEmployeeEntity.getPositionEmployeeCode().trim().trim())) {
                errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION)
                        .append(ErrorValue.API_UPDATE_DETAIL_POSITION).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
                errorItemName.append("positionEmployeeCode");
                error.setCode(errorItemCode.toString());
                error.setItemName(errorItemName.toString());
                return error;
            }
        }

        // Check name is null
        if (CheckValueService.checkNull(positionEmployeeEntity.getPositionEmployeeName().trim())) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_POSITION)
            .append(ErrorValue.API_UPDATE_DETAIL_POSITION)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("positionEmployeeName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        if (CheckValueService.checkMaxLength(positionEmployeeEntity.getPositionEmployeeCode().trim().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(positionEmployeeEntity.getPositionEmployeeDescription().trim(), SqlValue.LENGTH_DESCRIPTION)
                || CheckValueService.checkMaxLength(positionEmployeeEntity.getPositionEmployeeName().trim(), SqlValue.LENGTH_STRING)) {

            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION)
                    .append(ErrorValue.API_UPDATE_DETAIL_POSITION).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

        }
        if (CheckValueService.checkMaxLength(positionEmployeeEntity.getPositionEmployeeCode().trim().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("positionEmployeeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(positionEmployeeEntity.getPositionEmployeeDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemName.append("positionEmployeeDescription");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(positionEmployeeEntity.getPositionEmployeeName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("positionEmployeeName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (!currentPosition.isPresent()) {
            errorItemName.append("positionEmployeeID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION)
                    .append(ErrorValue.API_UPDATE_DETAIL_POSITION).append(ErrorValue.METHOD_PUT).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(positionEmployeeEntity.getPositionEmployeeCode().trim().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION)
            .append(ErrorValue.API_UPDATE_DETAIL_POSITION).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("positionEmployeeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse save(PositionEmployeeEntity positionEmployeeEntity, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<PositionEmployeeEntity> currentPosition = updateRepository
                .findByPositionEmployeeIDAndCompanyIDAndIsDelete(positionEmployeeEntity.getPositionEmployeeID(), companyID, false);
        ErrorResponse error = checkValue(positionEmployeeEntity, currentPosition, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS007002_UPDATE_EMPLOYEE_POSITION, positionEmployeeEntity, baseRes, "");
            return baseRes;
        }
        String positionEmployeeCode = positionEmployeeEntity.getPositionEmployeeCode().trim().toUpperCase();
        PositionEmployeeEntity pos = currentPosition.get();

        pos.setPositionEmployeeName(positionEmployeeEntity.getPositionEmployeeName().trim());
        pos.setIsDelete(positionEmployeeEntity.getIsDelete());
        pos.setPositionEmployeeCode(positionEmployeeCode);
        pos.setPositionEmployeeDescription(positionEmployeeEntity.getPositionEmployeeDescription().trim());
        updateRepository.save(pos);
        return baseRes;
    }
}
