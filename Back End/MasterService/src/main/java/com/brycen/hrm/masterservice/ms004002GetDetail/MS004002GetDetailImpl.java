package com.brycen.hrm.masterservice.ms004002GetDetail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.LevelSkillEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Get Details Service Implementation for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS004002GetDetailImpl implements MS004002GetDetailIService {

    @Autowired
    MS004002GetDetailIRepository ms004002GetDetailIRepository;
    
    @Autowired
    LoggerService logger;

    public ErrorResponse validation(long levelSkillID, Optional<LevelSkillEntity> currentLevelSkill) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        if (!currentLevelSkill.isPresent()) {
            errorItemName.append("levelSkillID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SKILL_LEVEL)
                    .append(ErrorValue.API_SEARCH_LIST_LEVEL_SKILL).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse getDetail(long levelSkillID, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<LevelSkillEntity> levelSkill = ms004002GetDetailIRepository.findByLevelSkillIDAndCompanyIDAndIsDelete(levelSkillID, companyID, false);
        ErrorResponse error = validation(levelSkillID, levelSkill);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS004002_GETDETAIL_SKILL_LEVEL, levelSkillID, baseRes, "");
            return baseRes;
        }
        LevelSkillEntity sl = levelSkill.get();
        MS004002GetDetailResonse searchOneRes = new MS004002GetDetailResonse();
        searchOneRes.setLevelSkillID(sl.getLevelSkillID());
        searchOneRes.setLevelSkillName(sl.getLevelSkillName());
        searchOneRes.setLevelSkillCode(sl.getLevelSkillCode());
        searchOneRes.setLevelSkillDescription(sl.getLevelSkillDescription());
        searchOneRes.setLevelSkillValue(sl.getLevelSkillValue());
        baseRes.setContent(searchOneRes);
        return baseRes;
    }

}
