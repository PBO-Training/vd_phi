package com.brycen.hrm.masterservice.ms002002GetDetail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.DepartmentEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]:MS002001GetDetailImpl<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS002002GetDetailImpl implements MS002002GetDetailService {
    @Autowired
    private MS002002GetDetailRepository searchOneRepository;

    @Autowired
    LoggerService logger;
    
    /**
     * [Description]:Validation<br/>
     * [ Remarks ]:<br/>
     *
     * @param departmentID
     * @param currentDepartment
     * @return
     */
    public ErrorResponse validation(long departmentID, Optional<DepartmentEntity> currentDepartment) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        if (!currentDepartment.isPresent()) {
            errorItemName.append("departmentID");
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_DEPARTMENT)
            .append(ErrorValue.API_SEARCH_LIST_DEPARTMENT)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse getDetail(long departmentID, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<DepartmentEntity> department = searchOneRepository.findByDepartmentIDAndCompanyIDAndIsDelete(departmentID, companyID, false);
        ErrorResponse error = validation(departmentID, department);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS002002_GETDETAIL_DEPARTMENT, departmentID, baseRes, "");
            return baseRes;
        }
        DepartmentEntity dep = department.get();
        MS002002GetDetailResponse searchOneRes = new MS002002GetDetailResponse();
        searchOneRes.setDepartmentID(dep.getDepartmentID());
        searchOneRes.setDepartmentName(dep.getDepartmentName());
        searchOneRes.setDepartmentCode(dep.getDepartmentCode());
        searchOneRes.setDepartmentDescription(dep.getDepartmentDescription());
        baseRes.setContent(searchOneRes);
        return baseRes;
    }
}
