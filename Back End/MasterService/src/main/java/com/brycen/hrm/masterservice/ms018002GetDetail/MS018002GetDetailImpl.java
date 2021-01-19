package com.brycen.hrm.masterservice.ms018002GetDetail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.RoleEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Get Details Service Implementation<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS018002GetDetailImpl implements MS018002GetDetailIService {

    /**
     * Call repository to find a role
     */
    @Autowired
    private MS018002GetDetailIRepository ms018002GetDetailIRepository;

    /**
     * Write log
     */
    @Autowired
    private LoggerService logger;

    public ErrorResponse validation(long roleID, Optional<RoleEntity> currentRole) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        if (!currentRole.isPresent()) {
            errorItemName.append("roleID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_ROLE)
                    .append(ErrorValue.API_SEARCH_LIST_ROLE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse getDetail(long roleID, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<RoleEntity> currentRole = ms018002GetDetailIRepository.findByRoleIDAndCompanyIDAndIsDelete(roleID, companyID, false);
        ErrorResponse error = validation(roleID, currentRole);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS008002_GETDETAIL_EMPLOYEE_STATUS, roleID, baseRes, "");
            return baseRes;
        }
        RoleEntity role = currentRole.get();
        MS018002GetDetailResponse searchOneRes = new MS018002GetDetailResponse();
        searchOneRes.setRoleID(role.getRoleID());
        searchOneRes.setRoleCode(role.getRoleCode());
        searchOneRes.setRoleName(role.getRoleName());
        searchOneRes.setRoleValue(role.getRoleValue());
        baseRes.setContent(searchOneRes);
        return baseRes;
    }

}
