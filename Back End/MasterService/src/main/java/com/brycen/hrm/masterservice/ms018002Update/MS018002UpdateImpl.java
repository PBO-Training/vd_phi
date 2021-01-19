package com.brycen.hrm.masterservice.ms018002Update;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.SqlValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.RoleEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Update Service Implementation for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS018002UpdateImpl implements MS018002UpdateIService {

    /**
     * Call repository to find or save a role
     */
    @Autowired
    private MS018002UpdateIRepository ms018002UpdateIRepository;

    /**
     * Write log
     */
    @Autowired
    private LoggerService logger;

    public ErrorResponse checkValue(RoleEntity roleEntity, Optional<RoleEntity> currentRole, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check duplicate code
        RoleEntity role = ms018002UpdateIRepository.findRoleByRoleCodeAndCompanyID(roleEntity.getRoleCode().trim(), companyID, currentRole.get().getRoleID());
        if (role != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_ROLE)
                    .append(ErrorValue.API_UPDATE_DETAIL_ROLE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("roleCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        } else {
            // check null code
            if (CheckValueService.checkNull(roleEntity.getRoleCode().trim())) {
                errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_ROLE)
                        .append(ErrorValue.API_UPDATE_DETAIL_ROLE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
                errorItemName.append("roleCode");
                error.setCode(errorItemCode.toString());
                error.setItemName(errorItemName.toString());
                return error;
            }
        }

        // Check name is null
        if (CheckValueService.checkNull(roleEntity.getRoleName().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_ROLE)
                    .append(ErrorValue.API_UPDATE_DETAIL_ROLE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("roleName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        if (CheckValueService.checkMaxLength(roleEntity.getRoleCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(roleEntity.getRoleName().trim(), SqlValue.LENGTH_STRING)) {

            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_ROLE)
                    .append(ErrorValue.API_UPDATE_DETAIL_ROLE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

        }
        if (CheckValueService.checkMaxLength(roleEntity.getRoleCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("roleCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(roleEntity.getRoleName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("roleName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (!currentRole.isPresent()) {
            errorItemName.append("roleID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_ROLE)
                    .append(ErrorValue.API_UPDATE_DETAIL_ROLE).append(ErrorValue.METHOD_PUT).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(roleEntity.getRoleCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_ROLE)
                    .append(ErrorValue.API_UPDATE_DETAIL_ROLE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("roleCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse save(RoleEntity roleEntity, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<RoleEntity> currentRole = ms018002UpdateIRepository.findByRoleIDAndCompanyIDAndIsDelete(roleEntity.getRoleID(), companyID, false);
        ErrorResponse error = checkValue(roleEntity, currentRole, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS018002_UPDATE_GROUP_ROLE, roleEntity, baseRes, "");
            return baseRes;
        }
        String roleCode = roleEntity.getRoleCode().trim().toUpperCase();
        RoleEntity role = currentRole.get();
        role.setIsDelete(roleEntity.getIsDelete());
        role.setRoleName(roleEntity.getRoleName().trim());
        role.setRoleValue(roleEntity.getRoleValue());
        role.setRoleCode(roleCode);
        ms018002UpdateIRepository.save(role);
        return baseRes;
    }

}
