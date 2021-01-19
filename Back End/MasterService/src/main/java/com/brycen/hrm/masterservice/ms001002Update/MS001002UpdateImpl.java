package com.brycen.hrm.masterservice.ms001002Update;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
 * [Description]: Implementation Update Service for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS001002UpdateImpl implements MS001002UpdateIService {

    /**
     * Support save an new user
     */
    @Autowired
    MS001002UpdateIRepository ms001002UpdateIRepository;

    /**
     * Support find a role by id
     */
    @Autowired
    MS001002UpdateIRoleRepository ms001002UpdateIRoleRepository;

    /**
     * Support find an employee by id
     */
    @Autowired
    MS001002UpdateIEmployeeRepository ms001002UpdateIEmployeeRepository;

    /**
     * Encode password
     */
    @Autowired
    PasswordEncoder encoder;

    /**
     * Support write log for api
     */
    @Autowired
    private LoggerService logger;

    public ErrorResponse checkValue(MS001002UpdateRequest ms001002UpdateRequest, Optional<UserEntity> currentUser, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check duplicate username
        UserEntity user = ms001002UpdateIRepository.findUserByUsernameAndCompanyID(ms001002UpdateRequest.getUsername().trim(), companyID,
                currentUser.get().getUserID());
        if (user != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_USER)
                    .append(ErrorValue.API_UPDATE_DETAIL_USER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("username");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        } else {
            // check null username
            if (CheckValueService.checkNull(ms001002UpdateRequest.getUsername().trim())) {
                errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_USER)
                        .append(ErrorValue.API_UPDATE_DETAIL_USER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
                errorItemName.append("username");
                error.setCode(errorItemCode.toString());
                error.setItemName(errorItemName.toString());
                return error;
            }
        }

        if (CheckValueService.checkMaxLength(ms001002UpdateRequest.getUsername().trim(), SqlValue.LENGTH_STRING)
                || CheckValueService.checkMaxLength(ms001002UpdateRequest.getPassword(), SqlValue.LENGTH_STRING)) {

            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_USER)
                    .append(ErrorValue.API_UPDATE_DETAIL_USER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

        }
        if (CheckValueService.checkMaxLength(ms001002UpdateRequest.getUsername().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("username");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(ms001002UpdateRequest.getPassword(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("password");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (!currentUser.isPresent()) {
            errorItemName.append("userID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_USER)
                    .append(ErrorValue.API_UPDATE_DETAIL_USER).append(ErrorValue.METHOD_PUT).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse updateUser(@RequestBody MS001002UpdateRequest ms001002UpdateRequest, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<UserEntity> currentUser = ms001002UpdateIRepository.findByUserIDAndCompanyIDAndIsDelete(ms001002UpdateRequest.getUserID(), companyID, false);
        ErrorResponse error = checkValue(ms001002UpdateRequest, currentUser, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS001002_UPDATE_USER, ms001002UpdateRequest, baseRes, "");
            return baseRes;
        }
        UserEntity u = currentUser.get();
        u.setUsername(ms001002UpdateRequest.getUsername().trim());
        if (!CheckValueService.checkNull(ms001002UpdateRequest.getPassword())) {
            u.setPassword(encoder.encode(ms001002UpdateRequest.getPassword()));
        }
        RoleEntity role = ms001002UpdateIRoleRepository.findByRoleIDAndCompanyIDAndIsDelete(ms001002UpdateRequest.getRoleID(), companyID, false).get();
        u.setRole(role);
        EmployeeEntity employee = ms001002UpdateIEmployeeRepository
                .findByEmployeeIDAndCompanyIDAndIsDelete(ms001002UpdateRequest.getEmployeeID(), companyID, false).get();
        u.setEmployee(employee);
        ms001002UpdateIRepository.save(u);
        return baseRes;
    }
}
