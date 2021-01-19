package com.brycen.hrm.masterservice.ms001002GetDetail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.UserEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Get Details Service Implementation for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS001002GetDetailImpl implements MS001002GetDetailIService {

    /**
     * Support find an user by ID
     */
    @Autowired
    MS001002GetDetailIRepository ms001002GetDetailIRepository;

    /**
     * Call log service to write log for api
     */
    @Autowired
    LoggerService logger;

    public ErrorResponse validation(Optional<UserEntity> currentUser) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        if (!currentUser.isPresent()) {
            errorItemName.append("userID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_USER)
                    .append(ErrorValue.API_SEARCH_LIST_USER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse findByID(long userID, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<UserEntity> user = ms001002GetDetailIRepository.findByUserIDAndCompanyIDAndIsDelete(userID, companyID, false);
        ErrorResponse error = validation(user);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS001002_GETDETAIL_USER, userID, baseRes, "");
            return baseRes;
        }
        UserEntity u = user.get();
        MS001002GetDetailUserResponse searchOneRes = new MS001002GetDetailUserResponse();
        searchOneRes.setUserID(u.getUserID());
        searchOneRes.setUsername(u.getUsername());
        searchOneRes.setPassword(u.getPassword());
        searchOneRes.setRole(new MS001002GetDetailRoleResponse(u.getRole()));
        searchOneRes.setEmployee(new MS001002GetDetailEmployeeResponse(u.getEmployee()));
        baseRes.setContent(searchOneRes);
        return baseRes;
    }

}
