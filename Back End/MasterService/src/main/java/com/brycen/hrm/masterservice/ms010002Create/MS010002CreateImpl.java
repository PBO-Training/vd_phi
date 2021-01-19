package com.brycen.hrm.masterservice.ms010002Create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.SqlValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.PositionProjectEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: This is place make to create new project position<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS010002CreateImpl implements MS010002CreateService {
    /**
     * Call to Repository and get methods to do actions Create new project position
     */
    @Autowired
    private MS010002CreateRepository createRepository;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    LoggerService logger;

    public ErrorResponse checkValue(PositionProjectEntity positionProjectEntity, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check project position code is null
        if (CheckValueService.checkNull(positionProjectEntity.getPositionProjectCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION_PROJECT)
                    .append(ErrorValue.API_CREATE_DETAIL_POSITION_PROJECT).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("positionProjectCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check project position code is duplicate
        PositionProjectEntity position = createRepository.findPositionByPositionProjectCodeAndCompanyID(positionProjectEntity.getPositionProjectCode().trim(),
                companyID);
        if (position != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION_PROJECT)
                    .append(ErrorValue.API_CREATE_DETAIL_POSITION_PROJECT).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("positionProjectCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check project position name is null
        if (CheckValueService.checkNull(positionProjectEntity.getPositionProjectName().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION_PROJECT)
                    .append(ErrorValue.API_CREATE_DETAIL_POSITION_PROJECT).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("positionProjectName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If positionProjectCode or positionProjectName is length illegal then set value to errorItemCode
        if (CheckValueService.checkMaxLength(positionProjectEntity.getPositionProjectCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(positionProjectEntity.getPositionProjectDescription().trim(), SqlValue.LENGTH_DESCRIPTION)
                || CheckValueService.checkMaxLength(positionProjectEntity.getPositionProjectName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION_PROJECT)
                    .append(ErrorValue.API_CREATE_DETAIL_POSITION_PROJECT).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
        }

        // If positionProjectCode is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(positionProjectEntity.getPositionProjectCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("positionProjectCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If positionDescription is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(positionProjectEntity.getPositionProjectDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemName.append("positionDescription");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If positionProjectName is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(positionProjectEntity.getPositionProjectName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("positionProjectName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(positionProjectEntity.getPositionProjectCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION_PROJECT)
            .append(ErrorValue.API_CREATE_DETAIL_POSITION_PROJECT).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("positionProjectCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse save(PositionProjectEntity positionProjectEntity, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        ErrorResponse error = checkValue(positionProjectEntity, companyID);

        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS010002_CREATE_POSITION_PROJECT, positionProjectEntity, baseRes, "");
            return baseRes;
        }

        String positionProjectCode = positionProjectEntity.getPositionProjectCode().trim().toUpperCase();
        positionProjectEntity.setCompanyID(companyID);
        positionProjectEntity.setPositionProjectCode(positionProjectCode);
        String positionProjectName = positionProjectEntity.getPositionProjectName().trim();
        positionProjectEntity.setPositionProjectName(positionProjectName);
        String positionProjectDescription = positionProjectEntity.getPositionProjectDescription().trim();
        positionProjectEntity.setPositionProjectDescription(positionProjectDescription);
        createRepository.save(positionProjectEntity);
        return baseRes;
    }
}
