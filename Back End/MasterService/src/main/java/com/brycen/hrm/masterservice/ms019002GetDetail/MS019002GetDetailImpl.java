package com.brycen.hrm.masterservice.ms019002GetDetail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.ScopeWorkEntity;
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
public class MS019002GetDetailImpl implements MS019002GetDetailIService {

    /**
     * Call repository to find a scopeWork
     */
    @Autowired
    private MS019002GetDetailIRepository ms019002GetDetailIRepository;

    /**
     * Write log
     */
    @Autowired
    private LoggerService logger;

    public ErrorResponse validation(long scopeWorkID, Optional<ScopeWorkEntity> currentScopeWork) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        if (!currentScopeWork.isPresent()) {
            errorItemName.append("scopeWorkID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SCOPE_WORK)
                    .append(ErrorValue.API_SEARCH_LIST_ROLE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse getDetail(long scopeWorkID, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<ScopeWorkEntity> currentScopeWork = ms019002GetDetailIRepository.findByScopeWorkIDAndCompanyIDAndIsDelete(scopeWorkID, companyID, false);
        ErrorResponse error = validation(scopeWorkID, currentScopeWork);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS008002_GETDETAIL_EMPLOYEE_STATUS, scopeWorkID, baseRes, "");
            return baseRes;
        }
        ScopeWorkEntity scopeWork = currentScopeWork.get();
        MS019002GetDetailResponse searchOneRes = new MS019002GetDetailResponse();
        searchOneRes.setScopeWorkID(scopeWork.getScopeWorkID());
        searchOneRes.setScopeWorkCode(scopeWork.getScopeWorkCode());
        searchOneRes.setScopeWorkName(scopeWork.getScopeWorkName());
        searchOneRes.setScopeWorkDescription(scopeWork.getScopeWorkDescription());
        baseRes.setContent(searchOneRes);
        return baseRes;
    }

}
