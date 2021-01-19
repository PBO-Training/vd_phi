package com.brycen.hrm.masterservice.ms002002Update;

import java.util.Optional;

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
 * [Description]: This is place to update department<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS002002UpdateImpl implements MS002002UpdateService {
    @Autowired
    private MS002002UpdateRepository updateRepository;

    @Autowired
    LoggerService logger;

    public ErrorResponse checkValue(DepartmentEntity departmentEntity, Optional<DepartmentEntity> currentDepartment, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check duplicate code
        DepartmentEntity department = updateRepository.findDepartmentByDepartmentCodeAndCompanyID(departmentEntity.getDepartmentCode().trim(), companyID,
                currentDepartment.get().getDepartmentID());
        if (department != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEPARTMENT)
                    .append(ErrorValue.API_UPDATE_DETAIL_DEPARTMENT).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("departmentCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        } else {
            // check null code
            if (CheckValueService.checkNull(departmentEntity.getDepartmentCode().trim())) {
                errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEPARTMENT)
                        .append(ErrorValue.API_UPDATE_DETAIL_DEPARTMENT).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
                errorItemName.append("departmentCode");
                error.setCode(errorItemCode.toString());
                error.setItemName(errorItemName.toString());
                return error;
            }
        }

        // Check department name is null
        if (CheckValueService.checkNull(departmentEntity.getDepartmentName().trim())) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_DEPARTMENT)
            .append(ErrorValue.API_UPDATE_DETAIL_DEPARTMENT)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("departmentName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        if (CheckValueService.checkMaxLength(departmentEntity.getDepartmentCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(departmentEntity.getDepartmentDescription().trim(), SqlValue.LENGTH_DESCRIPTION)
                || CheckValueService.checkMaxLength(departmentEntity.getDepartmentName().trim(), SqlValue.LENGTH_STRING)) {

            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEPARTMENT)
                    .append(ErrorValue.API_UPDATE_DETAIL_DEPARTMENT).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

        }
        if (CheckValueService.checkMaxLength(departmentEntity.getDepartmentCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("departmentCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(departmentEntity.getDepartmentDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemName.append("departmentDescription");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(departmentEntity.getDepartmentName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("departmentName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (!currentDepartment.isPresent()) {
            errorItemName.append("departmentID");
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_DEPARTMENT)
            .append(ErrorValue.API_UPDATE_DETAIL_DEPARTMENT)
            .append(ErrorValue.METHOD_PUT)
            .append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(departmentEntity.getDepartmentCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEPARTMENT)
            .append(ErrorValue.API_UPDATE_DETAIL_DEPARTMENT).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
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
        Optional<DepartmentEntity> currentDepartment = updateRepository.findByDepartmentIDAndCompanyIDAndIsDelete(departmentEntity.getDepartmentID(), companyID,
                false);
        ErrorResponse error = checkValue(departmentEntity, currentDepartment, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS002002_UPDATE_DEPARTMENT, departmentEntity, baseRes, "");
            return baseRes;
        }
        String departmentCode = departmentEntity.getDepartmentCode().trim().toUpperCase();
        DepartmentEntity dep = currentDepartment.get();
        dep.setDepartmentName(departmentEntity.getDepartmentName().trim());
        dep.setIsDelete(departmentEntity.getIsDelete());
        dep.setDepartmentCode(departmentCode);
        dep.setDepartmentDescription(departmentEntity.getDepartmentDescription().trim());
        updateRepository.save(dep);
        return baseRes;
    }
}
