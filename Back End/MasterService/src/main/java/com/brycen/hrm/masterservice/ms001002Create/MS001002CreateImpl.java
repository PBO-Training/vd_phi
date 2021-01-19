package com.brycen.hrm.masterservice.ms001002Create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.SqlValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.EmployeeEntity;
import com.brycen.hrm.entity.RoleEntity;
import com.brycen.hrm.entity.UserEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Create Service Implementation for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS001002CreateImpl implements MS001002CreateIService {

    /**
     * Support save an user
     */
    @Autowired
    MS001002CreateIUserRepository ms001002CreateIUserRepository;

    /**
     * Support find role by roleID
     */
    @Autowired
    MS001002CreateIRoleRepository ms001002CreateIRoleRepository;

    /**
     * Support find employee by employeeID
     */
    @Autowired
    MS001002CreateIEmployeeRepository ms001002CreateIEmployeeRepository;

    /**
     * Encoder password
     */
    @Autowired
    PasswordEncoder encoder;

    /**
     * Call log service to write log for api
     */
    @Autowired
    LoggerService logger;

    public ErrorResponse checkValue(MS001002CreateRequest createUserRequest, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check username is null
        if (CheckValueService.checkNull(createUserRequest.getUsername().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_USER)
                    .append(ErrorValue.API_CREATE_DETAIL_USER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("username");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check username code is duplicate
        UserEntity user = ms001002CreateIUserRepository.findUserByUsernameAndCompanyID(createUserRequest.getUsername().trim(), companyID);
        if (user != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_USER)
                    .append(ErrorValue.API_CREATE_DETAIL_USER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("username");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check password is null
        if (CheckValueService.checkNull(createUserRequest.getPassword())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_USER)
                    .append(ErrorValue.API_CREATE_DETAIL_USER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("password");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If username is length illegal then set value to errorItemCode
        if (CheckValueService.checkMaxLength(createUserRequest.getUsername().trim(), SqlValue.LENGTH_STRING)) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_USER)
                    .append(ErrorValue.API_CREATE_DETAIL_USER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
        }
        
        // If password is length illegal then set value to errorItemCode
        if (CheckValueService.checkMaxLength(createUserRequest.getPassword(), SqlValue.LENGTH_STRING)) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_USER)
                    .append(ErrorValue.API_CREATE_DETAIL_USER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
        }

        // If username is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(createUserRequest.getUsername().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("username");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If password is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(createUserRequest.getPassword(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("password");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        return null;
    }

    @Override
    public BaseResponse save(MS001002CreateRequest createUserRequest, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        ErrorResponse error = checkValue(createUserRequest, companyID);

        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS001002_CREATE_USER, createUserRequest, baseRes, "");
            return baseRes;
        }
        UserEntity user = new UserEntity(createUserRequest.getUsername().trim(), encoder.encode(createUserRequest.getPassword()));
        user.setCompanyID(companyID);
        RoleEntity roleUser = ms001002CreateIRoleRepository.findByRoleIDAndCompanyIDAndIsDelete(createUserRequest.getRoleID(), companyID, false).get();
        user.setRole(roleUser);
        EmployeeEntity employee = ms001002CreateIEmployeeRepository.findByEmployeeIDAndCompanyIDAndIsDelete(createUserRequest.getEmployeeID(), companyID, false)
                .get();
        user.setEmployee(employee);
        ms001002CreateIUserRepository.save(user);
        return baseRes;
    }
}
