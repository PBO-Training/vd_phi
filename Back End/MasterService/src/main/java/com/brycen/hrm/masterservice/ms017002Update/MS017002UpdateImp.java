package com.brycen.hrm.masterservice.ms017002Update;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.UtilFunction;
import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.gerenateErrorCode.ErrorCodeImplements;
import com.brycen.hrm.common.gerenateErrorCode.ErrorCodeObj;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.entity.ActionEntity;
import com.brycen.hrm.entity.RoleEntity;
import com.brycen.hrm.entity.ScreenEntity;
import com.brycen.hrm.repository.ActionIRepository;
import com.brycen.hrm.repository.RoleEntityIRepository;
import com.brycen.hrm.repository.ScreenIRepository;

@Service
public class MS017002UpdateImp implements MS017002UpdateService {

    @Autowired
    RoleEntityIRepository roleIRepository;

    @Autowired
    ScreenIRepository screenIRepository;

    @Autowired
    ActionIRepository actionIRepository;

    @Autowired
    public EntityManager em;

    @Autowired
    UtilFunction utilFunction;

    @Transactional
    @Override
    public BaseResponse update(List<MS017002UpdateRequest> listMs017002UpdateRequest, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        if (listMs017002UpdateRequest == null || listMs017002UpdateRequest.size() < 1) {
            return baseRes;
        }
        Session session = utilFunction.getCurrentSession().openSession();
        try {
            session.beginTransaction();
            StringBuilder sql = new StringBuilder();
            List<RoleEntity> listRole = roleIRepository.findByRoleCodeAndCompanyIDAndIsDelete(listMs017002UpdateRequest.get(0).getRoleCode(), companyID, false);
            if (listRole == null || listRole.size() < 1) {
                baseRes.setError(error("roleCode"));
                session.close();
                return baseRes;
            }
            Long roleID = listRole.get(0).getRoleID();
            for (int i = 0; i < listMs017002UpdateRequest.size(); i++) {
                Optional<ScreenEntity> screenEntity = screenIRepository
                        .findByScreenCodeAndCompanyIDAndIsDelete(listMs017002UpdateRequest.get(i).getScreenCode(), companyID, false);
                if (!screenEntity.isPresent()) {
                    baseRes.setError(error("screenCode"));
                    session.close();
                    return baseRes;
                }
                Long screenID = screenEntity.get().getScreenID();
                for (int j = 0; j < listMs017002UpdateRequest.get(i).getListAction().size(); j++) {
                    MS017002UpdateActionRequest ms017002UpdateActionRequest = listMs017002UpdateRequest.get(i).getListAction().get(j);
                    boolean access = ms017002UpdateActionRequest.isAccess();
                    sql.setLength(0);
                    if (ms017002UpdateActionRequest.getActionCode().equals("VIEW")) {
                        sql.append("UPDATE role_screen" + System.lineSeparator());
                        sql.append("SET access =" + access + System.lineSeparator());
                        sql.append("WHERE role_id =" + roleID + " AND screen_id =" + screenID);
                    } else {
                        Optional<ActionEntity> actionEntity = actionIRepository
                                .findByActionCodeAndCompanyIDAndIsDelete(ms017002UpdateActionRequest.getActionCode(), companyID, false);
                        if (!actionEntity.isPresent()) {
                            baseRes.setError(error("actionCode"));
                            session.close();
                            return baseRes;
                        }
                        Long actionID = actionEntity.get().getActionID();
                        sql.append("UPDATE role_screen_action" + System.lineSeparator());
                        sql.append("SET access =" + access + System.lineSeparator());
                        sql.append("WHERE role_id =" + roleID + " AND screen_id =" + screenID + " AND action_id =" + actionID);
                    }
                    session.createNativeQuery(sql.toString()).executeUpdate();
                }
            }
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
        } finally {
            session.flush();
            session.close();
        }
        return baseRes;
    }

    /**
     * [Description]:Validate not exist nameCode in database<br/>
     * [ Remarks ]:<br/>
     *
     * @param itemName
     * @return
     */
    private ErrorResponse error(String itemName) {
        ErrorCodeObj errorCode = new ErrorCodeObj(ErrorValue.TYPE_INPUT_VALUE_ERROR, ErrorValue.SERVICE_API_MASTER, ErrorValue.PACKAGE_ROLE_SCREEN,
                ErrorValue.API_UPDATE_DETAIL_ROLE_SCREEN, ErrorValue.METHOD_POST, ErrorValue.REASON_UNKNOWN_VALUE);
        ErrorCodeImplements error = new ErrorCodeImplements();
        error.generateErrorCode(errorCode);
        return new ErrorResponse(error.generateErrorCode(errorCode), itemName);
    }

}
