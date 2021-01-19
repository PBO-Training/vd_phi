package com.brycen.hrm.masterservice.ms002002Create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.SqlValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.DepartmentEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: This is place make to create new department<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS002002CreateImpl implements MS002002CreateService {
    @Autowired
    private MS002002CreateRepository createRepository;

    @Autowired
    LoggerService logger;

    public ErrorResponse checkValue(DepartmentEntity departmentEntity, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check department code is null
        if (CheckValueService.checkNull(departmentEntity.getDepartmentCode().trim())) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_DEPARTMENT)
            .append(ErrorValue.API_CREATE_DETAIL_DEPARTMENT)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("departmentCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check department code is duplicate
        DepartmentEntity department = createRepository.findDepartmentByDepartmentCodeAndCompanyID(departmentEntity.getDepartmentCode().trim(), companyID);
        if (department != null) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_DEPARTMENT)
            .append(ErrorValue.API_CREATE_DETAIL_DEPARTMENT)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("departmentCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check department name is null
        if (CheckValueService.checkNull(departmentEntity.getDepartmentName().trim())) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_DEPARTMENT)
            .append(ErrorValue.API_CREATE_DETAIL_DEPARTMENT)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("departmentName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If departmentCode or departmentName is length illegal then set value to errorItemCode
        if (CheckValueService.checkMaxLength(departmentEntity.getDepartmentCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(departmentEntity.getDepartmentDescription().trim(), SqlValue.LENGTH_DESCRIPTION)
                || CheckValueService.checkMaxLength(departmentEntity.getDepartmentName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEPARTMENT)
                    .append(ErrorValue.API_CREATE_DETAIL_DEPARTMENT).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
        }

        // If departmentCode is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(departmentEntity.getDepartmentCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("departmentCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If departmentDescription is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(departmentEntity.getDepartmentDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemName.append("departmentDescription");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If departmentName is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(departmentEntity.getDepartmentName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("departmentName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
     // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(departmentEntity.getDepartmentCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEPARTMENT)
            .append(ErrorValue.API_CREATE_DETAIL_DEPARTMENT).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("departmentCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse save(DepartmentEntity departmentEntity, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        ErrorResponse error = checkValue(departmentEntity, companyID);

        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS002002_CREATE_DEPARTMENT, departmentEntity, baseRes, "");
            return baseRes;
        }

        String departmentCode = departmentEntity.getDepartmentCode().trim().toUpperCase();
        departmentEntity.setCompanyID(companyID);
        departmentEntity.setDepartmentCode(departmentCode);
        String departmentName = departmentEntity.getDepartmentName().trim();
        departmentEntity.setDepartmentName(departmentName);
        String departmentDescription = departmentEntity.getDepartmentDescription().trim();
        departmentEntity.setDepartmentDescription(departmentDescription);
        createRepository.save(departmentEntity);
        return baseRes;
    }
}
