package com.brycen.hrm.masterservice.ms019002Create;

import javax.persistence.EntityManager;

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
 * [Description]: Create Service Implementation for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS019002CreateImpl implements MS019002CreateIService {

    /**
     * Supporting find a scope work with scope work code
     */
    @Autowired
    private MS019002CreateIRepository createRepository;

    /**
     * Call entity manager to support creation a native query statement
     */
    @Autowired
    public EntityManager em;

    /**
     * Write log
     */
    @Autowired
    private LoggerService logger;


    public ErrorResponse checkValue(MS019002CreateRequest ms019002CreateRequest, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check scope work code is null
        if (CheckValueService.checkNull(ms019002CreateRequest.getScopeWorkCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SCOPE_WORK)
                    .append(ErrorValue.API_CREATE_DETAIL_SCOPE_WORK).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("scopeWorkCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check scope work code is duplicate
        ScopeWorkEntity scopeWork = createRepository.findScopeWorkByScopeWorkCodeAndCompanyID(ms019002CreateRequest.getScopeWorkCode().trim(), companyID);
        if (scopeWork != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SCOPE_WORK)
                    .append(ErrorValue.API_CREATE_DETAIL_SCOPE_WORK).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("scopeWorkCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check scope work name is null
        if (CheckValueService.checkNull(ms019002CreateRequest.getScopeWorkName().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SCOPE_WORK)
                    .append(ErrorValue.API_CREATE_DETAIL_SCOPE_WORK).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("scopeWorkName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If ScopeWorkCode or ScopeWorkName is length illegal then set value to errorItemCode
        if (CheckValueService.checkMaxLength(ms019002CreateRequest.getScopeWorkCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(ms019002CreateRequest.getScopeWorkName().trim(), SqlValue.LENGTH_STRING)
                || CheckValueService.checkMaxLength(ms019002CreateRequest.getScopeWorkDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SCOPE_WORK)
                    .append(ErrorValue.API_CREATE_DETAIL_SCOPE_WORK).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
        }

        // If scopeWorkCode is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(ms019002CreateRequest.getScopeWorkCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("scopeWorkCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If scopeWorkName is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(ms019002CreateRequest.getScopeWorkName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("scopeWorkName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
     // If scopeWorkDescription is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(ms019002CreateRequest.getScopeWorkDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemName.append("scopeWorkDescription");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(ms019002CreateRequest.getScopeWorkCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SCOPE_WORK)
                    .append(ErrorValue.API_CREATE_DETAIL_SCOPE_WORK).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("scopeWorkCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse save(MS019002CreateRequest ms019002CreateRequest, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        ScopeWorkEntity scopeWork = new ScopeWorkEntity();
        ErrorResponse error = checkValue(ms019002CreateRequest, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS019002_CREATE_SCOPEWORK, ms019002CreateRequest, baseRes, "");
            return baseRes;
        }
        scopeWork.setCompanyID(companyID);
        scopeWork.setScopeWorkName(ms019002CreateRequest.getScopeWorkName().trim());
        scopeWork.setScopeWorkCode(ms019002CreateRequest.getScopeWorkCode().trim().toUpperCase());
        scopeWork.setScopeWorkDescription(ms019002CreateRequest.getScopeWorkDescription());
        createRepository.save(scopeWork);        
        return baseRes;
    }
}
