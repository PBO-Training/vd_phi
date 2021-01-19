package com.brycen.hrm.masterservice.ms007002GetDetail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.PositionEmployeeEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]:MS007002GetDetailImpl<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS007002GetDetailImpl implements MS007002GetDetailService {
    @Autowired
    private MS007002GetDetailRepository searchOneRepository;
    
    @Autowired
    LoggerService logger;

    /**
     * [Description]:Validation<br/>
     * [ Remarks ]:<br/>
     *
     * @param positionEmployeeID
     * @param currentPositionEmployee
     * @return
     */
    public ErrorResponse validation(long positionEmployeeID, Optional<PositionEmployeeEntity> currentPositionEmployee) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        if (!currentPositionEmployee.isPresent()) {
            errorItemName.append("positionEmployeeID");
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_POSITION)
            .append(ErrorValue.API_SEARCH_LIST_POSITION)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse getDetail(long positionEmployeeID, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<PositionEmployeeEntity> position = searchOneRepository.findByPositionEmployeeIDAndCompanyIDAndIsDelete(positionEmployeeID, companyID, false);
        ErrorResponse error = validation(positionEmployeeID, position);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS007002_GETDETAIL_EMPLOYEE_POSITION, positionEmployeeID, baseRes, "");
            return baseRes;
        }
        PositionEmployeeEntity dep = position.get();
        MS007002GetDetailResponse searchOneRes = new MS007002GetDetailResponse();
        searchOneRes.setPositionEmployeeID(dep.getPositionEmployeeID());
        searchOneRes.setPositionEmployeeName(dep.getPositionEmployeeName());
        searchOneRes.setPositionEmployeeCode(dep.getPositionEmployeeCode());
        searchOneRes.setPositionEmployeeDescription(dep.getPositionEmployeeDescription());
        baseRes.setContent(searchOneRes);
        return baseRes;
    }
}
