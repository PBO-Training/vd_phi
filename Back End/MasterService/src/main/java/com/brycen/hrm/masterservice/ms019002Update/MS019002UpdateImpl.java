package com.brycen.hrm.masterservice.ms019002Update;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.SqlValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.ScopeWorkEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Update Service Implementation for ScopeWork Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS019002UpdateImpl implements MS019002UpdateIService {

    /**
     * Call repository to find or save a Scope Work
     */
    @Autowired
    private MS019002UpdateIRepository ms019002UpdateIRepository;

    /**
     * Write log
     */
    @Autowired
    private LoggerService logger;

    public ErrorResponse checkValue(ScopeWorkEntity scopeWorkEntity, Optional<ScopeWorkEntity> currentScopeWork, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check duplicate code
        ScopeWorkEntity scopeWork = ms019002UpdateIRepository.findScopeWorkByScopeWorkCodeAndCompanyID(scopeWorkEntity.getScopeWorkCode().trim(), companyID,
                currentScopeWork.get().getScopeWorkID());
        if (scopeWork != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SCOPE_WORK)
                    .append(ErrorValue.API_UPDATE_DETAIL_SCOPE_WORK).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("scopeWorkCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        } else {
            // check null code
            if (CheckValueService.checkNull(scopeWorkEntity.getScopeWorkCode().trim())) {
                errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SCOPE_WORK)
                        .append(ErrorValue.API_UPDATE_DETAIL_SCOPE_WORK).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
                errorItemName.append("scopeWorkCode");
                error.setCode(errorItemCode.toString());
                error.setItemName(errorItemName.toString());
                return error;
            }
        }

        // Check name is null
        if (CheckValueService.checkNull(scopeWorkEntity.getScopeWorkName().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SCOPE_WORK)
                    .append(ErrorValue.API_UPDATE_DETAIL_SCOPE_WORK).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("scopeWorkName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        if (CheckValueService.checkMaxLength(scopeWorkEntity.getScopeWorkCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(scopeWorkEntity.getScopeWorkName().trim(), SqlValue.LENGTH_STRING)
                || CheckValueService.checkMaxLength(scopeWorkEntity.getScopeWorkDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SCOPE_WORK)
                    .append(ErrorValue.API_UPDATE_DETAIL_SCOPE_WORK).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

        }
        if (CheckValueService.checkMaxLength(scopeWorkEntity.getScopeWorkCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("scopeWorkCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(scopeWorkEntity.getScopeWorkName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("scopeWorkName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(scopeWorkEntity.getScopeWorkDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemName.append("scopeWorkDescription");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (!currentScopeWork.isPresent()) {
            errorItemName.append("scopeWorkID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SCOPE_WORK)
                    .append(ErrorValue.API_UPDATE_DETAIL_SCOPE_WORK).append(ErrorValue.METHOD_PUT).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(scopeWorkEntity.getScopeWorkCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SCOPE_WORK)
                    .append(ErrorValue.API_UPDATE_DETAIL_SCOPE_WORK).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("scopeWorkCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse save(ScopeWorkEntity scopeWorkEntity, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<ScopeWorkEntity> currentScopeWork = ms019002UpdateIRepository.findByScopeWorkIDAndCompanyIDAndIsDelete(scopeWorkEntity.getScopeWorkID(),
                companyID, false);
        ErrorResponse error = checkValue(scopeWorkEntity, currentScopeWork, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS019002_UPDATE_SCOPEWORK, scopeWorkEntity, baseRes, "");
            return baseRes;
        }
        String scopeWorkCode = scopeWorkEntity.getScopeWorkCode().trim().toUpperCase();
        ScopeWorkEntity scopeWork = currentScopeWork.get();
        scopeWork.setIsDelete(scopeWorkEntity.getIsDelete());
        scopeWork.setScopeWorkName(scopeWorkEntity.getScopeWorkName().trim());
        scopeWork.setScopeWorkDescription(scopeWorkEntity.getScopeWorkDescription().trim());
        scopeWork.setScopeWorkCode(scopeWorkCode);
        ms019002UpdateIRepository.save(scopeWork);
        return baseRes;
    }
}
