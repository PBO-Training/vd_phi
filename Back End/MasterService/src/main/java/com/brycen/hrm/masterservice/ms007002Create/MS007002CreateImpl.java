package com.brycen.hrm.masterservice.ms007002Create;

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
 * [Description]: This is place make to create new position<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS007002CreateImpl implements MS007002CreateService {
    @Autowired
    private MS007002CreateRepository createRepository;
    
    @Autowired
    LoggerService logger;
    
    public ErrorResponse checkValue(PositionEmployeeEntity positionEmployeeEntity, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check code is null
        if (CheckValueService.checkNull(positionEmployeeEntity.getPositionEmployeeCode().trim())) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_POSITION)
            .append(ErrorValue.API_CREATE_DETAIL_POSITION)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("positionEmployeeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check code is duplicate
        PositionEmployeeEntity positionEmployee = createRepository.findPositionEmployeeByPositionEmployeeCodeAndCompanyID(positionEmployeeEntity.getPositionEmployeeCode().trim(), companyID);
        if (positionEmployee != null) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_POSITION)
            .append(ErrorValue.API_CREATE_DETAIL_POSITION)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("positionEmployeeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check name is null
        if (CheckValueService.checkNull(positionEmployeeEntity.getPositionEmployeeName().trim())) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_POSITION)
            .append(ErrorValue.API_CREATE_DETAIL_POSITION)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("positionEmployeeName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        if (CheckValueService.checkMaxLength(positionEmployeeEntity.getPositionEmployeeCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(positionEmployeeEntity.getPositionEmployeeDescription().trim(), SqlValue.LENGTH_DESCRIPTION)
                || CheckValueService.checkMaxLength(positionEmployeeEntity.getPositionEmployeeName().trim(), SqlValue.LENGTH_STRING)) {

            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION)
                    .append(ErrorValue.API_CREATE_DETAIL_POSITION).append(ErrorValue.METHOD_POST);

        }
        if (CheckValueService.checkMaxLength(positionEmployeeEntity.getPositionEmployeeCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("positionEmployeeCode");
            errorItemCode.append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(positionEmployeeEntity.getPositionEmployeeDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemName.append("positionEmployeeDescription");
            errorItemCode.append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(positionEmployeeEntity.getPositionEmployeeName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("positionEmployeeName");
            errorItemCode.append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(positionEmployeeEntity.getPositionEmployeeCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION)
            .append(ErrorValue.API_CREATE_DETAIL_POSITION).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
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
        ErrorResponse error = checkValue(positionEmployeeEntity, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS007002_CREATE_EMPLOYEE_POSITION, positionEmployeeEntity, baseRes, "");
            return baseRes;
        }
        String positionEmployeeCode = positionEmployeeEntity.getPositionEmployeeCode().trim().toUpperCase();
        positionEmployeeEntity.setCompanyID(companyID);
        positionEmployeeEntity.setPositionEmployeeCode(positionEmployeeCode);
        String positionEmployeeName = positionEmployeeEntity.getPositionEmployeeName().trim();
        positionEmployeeEntity.setPositionEmployeeName(positionEmployeeName);
        String positionEmployeeDescription = positionEmployeeEntity.getPositionEmployeeDescription().trim();
        positionEmployeeEntity.setPositionEmployeeDescription(positionEmployeeDescription);
        createRepository.save(positionEmployeeEntity);
        return baseRes;
    }
}
