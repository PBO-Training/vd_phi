package com.brycen.hrm.masterservice.ms010002Update;

import java.util.Optional;

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
 * [Description]: This is place to update project position<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS010002UpdateImpl implements MS010002UpdateService {
    /**
     * Call to Repository and get methods to do actions update project position
     */
    @Autowired
    private MS010002UpdateRepository updateRepository;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    LoggerService logger;

    public ErrorResponse checkValue(PositionProjectEntity positionProjectEntity, Optional<PositionProjectEntity> currentPosition, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check duplicate code
        PositionProjectEntity positionProject = updateRepository.findPositionProjectByPositionProjectCodeAndCompanyID(
                positionProjectEntity.getPositionProjectCode().trim(), companyID, currentPosition.get().getPositionProjectID());
        if (positionProject != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION_PROJECT)
                    .append(ErrorValue.API_UPDATE_DETAIL_POSITION_PROJECT).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("positionProjectCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        } else {
            // check null code
            if (CheckValueService.checkNull(positionProjectEntity.getPositionProjectCode().trim())) {
                errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION_PROJECT)
                        .append(ErrorValue.API_UPDATE_DETAIL_POSITION_PROJECT).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
                errorItemName.append("positionProjectCode");
                error.setCode(errorItemCode.toString());
                error.setItemName(errorItemName.toString());
                return error;
            }
        }

     // Check project position name is null
        if (CheckValueService.checkNull(positionProjectEntity.getPositionProjectName().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION_PROJECT)
                    .append(ErrorValue.API_UPDATE_DETAIL_POSITION_PROJECT).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("positionProjectName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        if (CheckValueService.checkMaxLength(positionProjectEntity.getPositionProjectCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(positionProjectEntity.getPositionProjectDescription().trim(), SqlValue.LENGTH_DESCRIPTION)
                || CheckValueService.checkMaxLength(positionProjectEntity.getPositionProjectName().trim(), SqlValue.LENGTH_STRING)) {

            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION_PROJECT)
                    .append(ErrorValue.API_UPDATE_DETAIL_POSITION_PROJECT).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

        }
        if (CheckValueService.checkMaxLength(positionProjectEntity.getPositionProjectCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("positionProjectCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(positionProjectEntity.getPositionProjectDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemName.append("positionProjectDescription");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(positionProjectEntity.getPositionProjectName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("positionProjectName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (!currentPosition.isPresent()) {
            errorItemName.append("positionProjectID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION_PROJECT)
                    .append(ErrorValue.API_UPDATE_DETAIL_POSITION_PROJECT).append(ErrorValue.METHOD_PUT).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(positionProjectEntity.getPositionProjectCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION_PROJECT)
            .append(ErrorValue.API_UPDATE_DETAIL_POSITION_PROJECT).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("positionProjectCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    /**
     * [Description]: Method find a project position with id specification and update it<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return BaseResponse - Model contain data what need to send to client
     */
    @Override
    public BaseResponse save(PositionProjectEntity positionProjectEntity, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<PositionProjectEntity> currentPosition = updateRepository
                .findByPositionProjectIDAndCompanyIDAndIsDelete(positionProjectEntity.getPositionProjectID(), companyID, false);
        ErrorResponse error = checkValue(positionProjectEntity, currentPosition, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS010002_UPDATE_POSITION_PROJECT, positionProjectEntity, baseRes, "");
            return baseRes;
        }
        String positionProjectCode = positionProjectEntity.getPositionProjectCode().trim().toUpperCase();
        PositionProjectEntity pos = currentPosition.get();
        pos.setPositionProjectName(positionProjectEntity.getPositionProjectName().trim());
        pos.setIsDelete(positionProjectEntity.getIsDelete());
        pos.setPositionProjectCode(positionProjectCode);
        pos.setPositionProjectDescription(positionProjectEntity.getPositionProjectDescription().trim());
        updateRepository.save(pos);
        return baseRes;
    }
}
