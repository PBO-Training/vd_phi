package com.brycen.hrm.masterservice.ms012002GetDetail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.VacationTypeEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]:MS012001GetDetailImpl<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS012002GetDetailImpl implements MS012002GetDetailService {
    /**
     * Call to Repository and get methods to do actions Get detail project vacation type
     */
    @Autowired
    private MS012002GetDetailRepository searchOneRepository;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    LoggerService logger;

    /**
     * [Description]:Validation<br/>
     * [ Remarks ]:<br/>
     *
     * @param vacationTypeID
     * @param currentvacationType
     * @return
     */
    public ErrorResponse validation(long vacationTypeID, Optional<VacationTypeEntity> currentvacationType) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        if (!currentvacationType.isPresent()) {
            errorItemName.append("vacationTypeID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_TYPE_VACATION)
                    .append(ErrorValue.API_SEARCH_LIST_VACATION).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse getDetail(long vacationTypeID, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<VacationTypeEntity> vacationType = searchOneRepository.findByvacationTypeIDAndCompanyIDAndIsDelete(vacationTypeID, companyID, false);
        ErrorResponse error = validation(vacationTypeID, vacationType);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS012002_GETDETAIL_VACATION_TYPE, vacationTypeID, baseRes, "");
            return baseRes;
        }
        VacationTypeEntity vac = vacationType.get();
        MS012002GetDetailResponse searchOneRes = new MS012002GetDetailResponse();
        searchOneRes.setVacationTypeID(vac.getVacationTypeID());
        searchOneRes.setVacationTypeName(vac.getVacationTypeName());
        searchOneRes.setVacationTypeCode(vac.getVacationTypeCode());
        searchOneRes.setVacationTypeDescription(vac.getVacationTypeDescription());
        baseRes.setContent(searchOneRes);
        return baseRes;
    }
}
