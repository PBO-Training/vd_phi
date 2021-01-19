package com.brycen.hrm.masterservice.ms018002Create;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.SqlValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.ActionEntity;
import com.brycen.hrm.entity.RoleEntity;
import com.brycen.hrm.entity.ScreenEntity;
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
public class MS018002CreateImpl implements MS018002CreateIService {

    /**
     * Supporting find a role with role code
     */
    @Autowired
    private MS018002CreateIRepository createRepository;

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

    /**
     * Call query to save an instance native query statement
     */
    private Query query;

    public ErrorResponse checkValue(MS018002CreateRequest ms018002CreateRequest, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check role code is null
        if (CheckValueService.checkNull(ms018002CreateRequest.getRoleCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_ROLE)
                    .append(ErrorValue.API_CREATE_DETAIL_ROLE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("roleCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check role code is duplicate
        RoleEntity role = createRepository.findRoleByRoleCodeAndCompanyID(ms018002CreateRequest.getRoleCode().trim(), companyID);
        if (role != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_ROLE)
                    .append(ErrorValue.API_CREATE_DETAIL_ROLE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("roleCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check role name is null
        if (CheckValueService.checkNull(ms018002CreateRequest.getRoleName().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_ROLE)
                    .append(ErrorValue.API_CREATE_DETAIL_ROLE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("roleName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If departmentCode or departmentName is length illegal then set value to errorItemCode
        if (CheckValueService.checkMaxLength(ms018002CreateRequest.getRoleCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(ms018002CreateRequest.getRoleName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_ROLE)
                    .append(ErrorValue.API_CREATE_DETAIL_ROLE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
        }

        // If roleCode is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(ms018002CreateRequest.getRoleCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("roleCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // If roleName is length illegal then set value to errorItemName and return
        if (CheckValueService.checkMaxLength(ms018002CreateRequest.getRoleName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("roleName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(ms018002CreateRequest.getRoleCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_ROLE)
                    .append(ErrorValue.API_CREATE_DETAIL_ROLE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("roleCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @SuppressWarnings({ "unchecked" })
    @Transactional
    @Override
    public BaseResponse save(MS018002CreateRequest ms018002CreateRequest, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        RoleEntity role = new RoleEntity();
        ErrorResponse error = checkValue(ms018002CreateRequest, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS018002_CREATE_GROUP_ROLE, ms018002CreateRequest, baseRes, "");
            return baseRes;
        }
        role.setCompanyID(companyID);
        role.setRoleName(ms018002CreateRequest.getRoleName().trim());
        role.setRoleCode(ms018002CreateRequest.getRoleCode().trim().toUpperCase());
        role.setRoleValue(ms018002CreateRequest.getRoleValue());
        createRepository.save(role);
        long roleID = role.getRoleID();

        StringBuffer selectString = new StringBuffer("SELECT * ");
        StringBuffer fromString = new StringBuffer("FROM screen s ");
        StringBuffer whereString = new StringBuffer("WHERE s.company_id = :companyID ");
        StringBuffer andString = new StringBuffer("AND s.is_delete = 0 ");
        StringBuffer queryString = new StringBuffer();
        queryString.append(selectString).append(fromString).append(whereString).append(andString);
        query = em.createNativeQuery(queryString.toString(), ScreenEntity.class);
        query.setParameter("companyID", companyID);
        List<ScreenEntity> listScreen = query.getResultList();

        StringBuffer insertString = new StringBuffer("INSERT INTO role_screen (role_id, screen_id, access) VALUES ");
        StringBuffer roleString = new StringBuffer("(");
        StringBuffer accessString = new StringBuffer(",0)");
        StringBuffer executeString = new StringBuffer();
        executeString.append(insertString);
        for (int i = 0; i < listScreen.size(); i++) {
            if (i == listScreen.size() - 1) {
                executeString.append(roleString).append(roleID).append(",").append(listScreen.get(i).getScreenID()).append(accessString);
            } else {
                executeString.append(roleString).append(roleID).append(",").append(listScreen.get(i).getScreenID()).append(accessString).append(",");
            }
        }
        query = em.createNativeQuery(executeString.toString());
        query.executeUpdate();

        StringBuffer fromString1 = new StringBuffer("FROM action a ");
        StringBuffer whereString1 = new StringBuffer("WHERE a.company_id = :companyID ");
        StringBuffer andString1 = new StringBuffer("AND a.is_delete = 0 ");
        StringBuffer queryString1 = new StringBuffer();
        queryString1.append(selectString).append(fromString1).append(whereString1).append(andString1);
        query = em.createNativeQuery(queryString1.toString(), ActionEntity.class);
        query.setParameter("companyID", companyID);
        List<ActionEntity> listAction = query.getResultList();

        StringBuffer insertString1 = new StringBuffer("INSERT INTO role_screen_action (action_id, role_id, screen_id, access) VALUES ");
        StringBuffer roleString1 = new StringBuffer("(");
        StringBuffer accessString1 = new StringBuffer(",0)");
        StringBuffer executeString1 = new StringBuffer();
        executeString1.append(insertString1);
        for (int i = 0; i < listAction.size(); i++) {
            for (int j = 0; j < listScreen.size(); j++) {
                if (j == listScreen.size() - 1) {
                    executeString1.append(roleString1).append(listAction.get(i).getActionID()).append(",").append(roleID).append(",")
                            .append(listScreen.get(j).getScreenID()).append(accessString1);
                } else {
                    executeString1.append(roleString1).append(listAction.get(i).getActionID()).append(",").append(roleID).append(",")
                            .append(listScreen.get(j).getScreenID()).append(accessString1).append(",");
                }
            }
            if (i != listAction.size() - 1) {
                executeString1.append(",");
            }
        }
        query = em.createNativeQuery(executeString1.toString());
        query.executeUpdate();
        return baseRes;
    }
}
