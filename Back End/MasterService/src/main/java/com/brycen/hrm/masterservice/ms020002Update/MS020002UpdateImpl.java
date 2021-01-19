package com.brycen.hrm.masterservice.ms020002Update;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.SqlValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.DegreeEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: This is place to update degree<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS020002UpdateImpl implements MS020002UpdateService {
    /**
     * Call to Repository and get methods to do actions update degree
     */
    @Autowired
    private MS020002UpdateRepository updateRepository;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    LoggerService logger;

    public ErrorResponse checkValue(DegreeEntity degreeEntity, Optional<DegreeEntity> currentdegree, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check duplicate code
        DegreeEntity degree = updateRepository.findDegreeByDegreeCodeAndCompanyID(degreeEntity.getDegreeCode().trim(), companyID,
                currentdegree.get().getDegreeID());
        if (degree != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEGREE)
                    .append(ErrorValue.API_UPDATE_DETAIL_DEGREE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("degreeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        } else {
            // check null code
            if (CheckValueService.checkNull(degreeEntity.getDegreeCode().trim())) {
                errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEGREE)
                        .append(ErrorValue.API_UPDATE_DETAIL_DEGREE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
                errorItemName.append("degreeCode");
                error.setCode(errorItemCode.toString());
                error.setItemName(errorItemName.toString());
                return error;
            }
        }

        // Check name is null
        if (CheckValueService.checkNull(degreeEntity.getDegreeName().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEGREE)
                    .append(ErrorValue.API_UPDATE_DETAIL_DEGREE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("degreeName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        if (CheckValueService.checkMaxLength(degreeEntity.getDegreeCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(degreeEntity.getDegreeDescription().trim(), SqlValue.LENGTH_DESCRIPTION)
                || CheckValueService.checkMaxLength(degreeEntity.getDegreeName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEGREE)
                    .append(ErrorValue.API_UPDATE_DETAIL_DEGREE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

        }
        if (CheckValueService.checkMaxLength(degreeEntity.getDegreeCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("degreeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(degreeEntity.getDegreeDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemName.append("degreeDescription");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(degreeEntity.getDegreeName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("degreeName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (!currentdegree.isPresent()) {
            errorItemName.append("degreeID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEGREE)
                    .append(ErrorValue.API_UPDATE_DETAIL_DEGREE).append(ErrorValue.METHOD_PUT).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(degreeEntity.getDegreeCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEGREE)
                    .append(ErrorValue.API_UPDATE_DETAIL_DEGREE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("degreeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    /**
     * [Description]: Method find a degree with id specification and update it<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return BaseResponse - Model contain data what need to send to client
     */
    @Override
    public BaseResponse save(DegreeEntity degreeEntity, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<DegreeEntity> currentdegree = updateRepository.findByDegreeIDAndCompanyIDAndIsDelete(degreeEntity.getDegreeID(), companyID, false);
        ErrorResponse error = checkValue(degreeEntity, currentdegree, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS020002_UPDATE_DEGREE, degreeEntity, baseRes, "");
            return baseRes;
        }
        String degreeCode = degreeEntity.getDegreeCode().trim().toUpperCase();
        DegreeEntity pos = currentdegree.get();
        pos.setDegreeName(degreeEntity.getDegreeName().trim());
        pos.setIsDelete(degreeEntity.getIsDelete());
        pos.setDegreeCode(degreeCode);
        pos.setDegreeDescription(degreeEntity.getDegreeDescription().trim());
        updateRepository.save(pos);
        return baseRes;
    }
}
