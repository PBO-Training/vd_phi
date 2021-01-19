package com.brycen.hrm.masterservice.ms003002GetDetail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.SkillEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]:MS003002GetDetailImpl<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS003002GetDetailImpl implements MS003002GetDetailService {
    @Autowired
    private MS003002GetDetailRepository searchOneRepository;
    
    @Autowired
    LoggerService logger;

    /**
     * [Description]:Validation<br/>
     * [ Remarks ]:<br/>
     *
     * @param skillID
     * @param currentSkill
     * @return
     */
    public ErrorResponse validation(long skillID, Optional<SkillEntity> currentSkill) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        if (!currentSkill.isPresent()) {
            errorItemName.append("skillID");
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_SKILL)
            .append(ErrorValue.API_SEARCH_LIST_SKILL)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse getDetail(long skillID, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<SkillEntity> skill = searchOneRepository.findBySkillIDAndCompanyIDAndIsDelete(skillID, companyID, false);
        ErrorResponse error = validation(skillID, skill);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS003002_GETDETAIL_SKILL, skillID, baseRes, "");
            return baseRes;
        }
        SkillEntity dep = skill.get();
        MS003002GetDetailResponse searchOneRes = new MS003002GetDetailResponse();
        searchOneRes.setSkillID(dep.getSkillID());
        searchOneRes.setSkillName(dep.getSkillName());
        searchOneRes.setSkillCode(dep.getSkillCode());
        searchOneRes.setSkillDescription(dep.getSkillDescription());
        searchOneRes.setSkillTypeID(dep.getSkillType().getSkillTypeID());
        baseRes.setContent(searchOneRes);
        return baseRes;
    }
}
