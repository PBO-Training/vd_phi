package com.brycen.hrm.masterservice.ms017002GetDetail;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.gerenateErrorCode.ErrorCodeImplements;
import com.brycen.hrm.common.gerenateErrorCode.ErrorCodeObj;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.entity.RoleEntity;
import com.brycen.hrm.masterservice.ms017002GetDetail.dto.MS017002GetDetailResponse;
import com.brycen.hrm.repository.RoleEntityIRepository;

@Service
public class MS017002GetDetailImpl implements MS017002GetDetailService {

    @Autowired
    RoleEntityIRepository roleRepository;

    @Override
    public BaseResponse getDetail(MS017002GetDetailRequest getDetailRequest, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        List<RoleEntity> listRole = roleRepository.findByRoleCodeAndCompanyIDAndIsDelete(getDetailRequest.getRoleCode(), companyID, false);
        ErrorResponse error = validation(listRole);
        if (error != null) {
            baseRes.setError(error);
            return baseRes;
        }
        List<MS017002GetDetailResponse> roles = listRole.stream().map(MS017002GetDetailResponse::new).collect(Collectors.toList());
        baseRes.setContent(roles);
        return baseRes;
    }

    /**
     * [Description]:Validation RoleCode<br/>
     * [ Remarks ]:<br/>
     *
     * @param listRole
     * @return ErrorResponse
     */
    private ErrorResponse validation(List<RoleEntity> listRole) {
        if(listRole == null || listRole.size() < 1) {
            ErrorCodeObj errorCode = new ErrorCodeObj(
                    ErrorValue.TYPE_INPUT_VALUE_ERROR, 
                    ErrorValue.SERVICE_API_MASTER, 
                    ErrorValue.PACKAGE_ROLE_SCREEN,
                    ErrorValue.API_UPDATE_DETAIL_ROLE_SCREEN, 
                    ErrorValue.METHOD_POST, 
                    ErrorValue.REASON_UNKNOWN_VALUE);
            ErrorCodeImplements error = new ErrorCodeImplements();
            error.generateErrorCode(errorCode);
            return new ErrorResponse(error.generateErrorCode(errorCode),"roleCode");
        }
        return null;
    }
}
