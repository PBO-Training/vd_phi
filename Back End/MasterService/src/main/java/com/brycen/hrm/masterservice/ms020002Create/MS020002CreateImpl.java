package com.brycen.hrm.masterservice.ms020002Create;

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
 * [Description]: This is place make to create new Degree<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS020002CreateImpl implements MS020002CreateService {
    /**
     * Call to Repository and get methods to do actions Create new Degree
     */
    @Autowired
    private MS020002CreateRepository createRepository;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    LoggerService logger;

    public ErrorResponse checkValue(DegreeEntity degreeEntity, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check Degree code is null
        if (CheckValueService.checkNull(degreeEntity.getDegreeCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEGREE)
                    .append(ErrorValue.API_CREATE_DETAIL_DEGREE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("degreeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check Degree name is null
        if (CheckValueService.checkNull(degreeEntity.getDegreeName().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEGREE)
                    .append(ErrorValue.API_CREATE_DETAIL_DEGREE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("degreeName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check Degree code is duplicate
        DegreeEntity degree = createRepository.findDegreeByDegreeCodeAndCompanyID(degreeEntity.getDegreeCode().trim(), companyID);
        if (degree != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEGREE)
                    .append(ErrorValue.API_CREATE_DETAIL_DEGREE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("degreeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If degreeCode or degreeName is length illegal then set value to errorItemCode
        if (CheckValueService.checkMaxLength(degreeEntity.getDegreeCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(degreeEntity.getDegreeDescription().trim(), SqlValue.LENGTH_DESCRIPTION)
                || CheckValueService.checkMaxLength(degreeEntity.getDegreeName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEGREE)
                    .append(ErrorValue.API_CREATE_DETAIL_DEGREE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
        }

        // If degreeCode is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(degreeEntity.getDegreeCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("degreeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If degreeDescription is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(degreeEntity.getDegreeDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemName.append("degreeDescription");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If degreeName is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(degreeEntity.getDegreeName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("degreeName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(degreeEntity.getDegreeCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEGREE)
            .append(ErrorValue.API_CREATE_DETAIL_DEGREE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("degreeCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse save(DegreeEntity degreeEntity, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        ErrorResponse error = checkValue(degreeEntity, companyID);

        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS020002_CREATE_DEGREE, degreeEntity, baseRes, "");
            return baseRes;
        }

        String degreeCode = degreeEntity.getDegreeCode().trim().toUpperCase();
        degreeEntity.setCompanyID(companyID);
        degreeEntity.setDegreeCode(degreeCode);
        String degreeName = degreeEntity.getDegreeName().trim();
        degreeEntity.setDegreeName(degreeName);
        String degreeDescription = degreeEntity.getDegreeDescription().trim();
        degreeEntity.setDegreeDescription(degreeDescription);
        createRepository.save(degreeEntity);
        return baseRes;
    }
}
