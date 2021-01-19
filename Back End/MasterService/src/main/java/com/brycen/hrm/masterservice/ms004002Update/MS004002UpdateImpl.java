package com.brycen.hrm.masterservice.ms004002Update;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.SqlValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.LevelSkillEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Update Service Implementation for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS004002UpdateImpl implements MS004002UpdateIService {

    @Autowired
    private MS004002UpdateIRepository ms004002UpdateIRepository;
    
    @Autowired
    LoggerService logger;
    
    public ErrorResponse checkValue(LevelSkillEntity levelSkillEntity, Optional<LevelSkillEntity> currentLevelSkill, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check duplicate code
        LevelSkillEntity levelSkill = ms004002UpdateIRepository.findLevelSkillByLevelSkillCodeAndCompanyID(levelSkillEntity.getLevelSkillCode().trim(), companyID,
                currentLevelSkill.get().getLevelSkillID());
        if (levelSkill != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SKILL_LEVEL)
                    .append(ErrorValue.API_UPDATE_DETAIL_LEVEL_SKILL).append(ErrorValue.METHOD_PUT).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("levelSkillCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        } else {
            // check null code
            if (CheckValueService.checkNull(levelSkillEntity.getLevelSkillCode().trim())) {
                errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SKILL_LEVEL)
                        .append(ErrorValue.API_UPDATE_DETAIL_LEVEL_SKILL).append(ErrorValue.METHOD_PUT).append(ErrorValue.REASON_VALUE_UNSETTING);
                errorItemName.append("levelSkillCode");
                error.setCode(errorItemCode.toString());
                error.setItemName(errorItemName.toString());
                return error;
            }
        }

        // Check name is null
        if (CheckValueService.checkNull(levelSkillEntity.getLevelSkillName().trim())) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_SKILL_LEVEL)
            .append(ErrorValue.API_UPDATE_DETAIL_LEVEL_SKILL)
            .append(ErrorValue.METHOD_PUT)
            .append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("levelSkillName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        if (CheckValueService.checkMaxLength(levelSkillEntity.getLevelSkillCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(levelSkillEntity.getLevelSkillDescription().trim(), SqlValue.LENGTH_DESCRIPTION)
                || CheckValueService.checkMaxLength(levelSkillEntity.getLevelSkillName().trim(), SqlValue.LENGTH_NAME)) {

            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SKILL_LEVEL)
                    .append(ErrorValue.API_UPDATE_DETAIL_LEVEL_SKILL).append(ErrorValue.METHOD_PUT).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

        }
        if (CheckValueService.checkMaxLength(levelSkillEntity.getLevelSkillCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("levelSkillCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(levelSkillEntity.getLevelSkillDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemName.append("levelSkillDescription");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(levelSkillEntity.getLevelSkillName().trim(), SqlValue.LENGTH_NAME)) {
            errorItemName.append("levelSkillName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (!currentLevelSkill.isPresent()) {
            errorItemName.append("levelSkillID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SKILL_LEVEL)
                    .append(ErrorValue.API_UPDATE_DETAIL_LEVEL_SKILL).append(ErrorValue.METHOD_PUT).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(levelSkillEntity.getLevelSkillCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SKILL_LEVEL)
            .append(ErrorValue.API_UPDATE_DETAIL_LEVEL_SKILL).append(ErrorValue.METHOD_PUT).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("levelSkillCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse save(LevelSkillEntity levelSkillEntity, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<LevelSkillEntity> currentLevelSkill = ms004002UpdateIRepository.findByLevelSkillIDAndCompanyIDAndIsDelete(levelSkillEntity.getLevelSkillID(),
                companyID, false);
        ErrorResponse error = checkValue(levelSkillEntity, currentLevelSkill, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS004002_UPDATE_SKILL_LEVEL, levelSkillEntity, baseRes, "");
            return baseRes;
        }
        String levelSkillCode = levelSkillEntity.getLevelSkillCode().trim().toUpperCase();
        LevelSkillEntity ls = currentLevelSkill.get();
        ls.setLevelSkillName(levelSkillEntity.getLevelSkillName().trim());
        ls.setIsDelete(levelSkillEntity.getIsDelete());
        ls.setLevelSkillCode(levelSkillCode);
        ls.setLevelSkillDescription(levelSkillEntity.getLevelSkillDescription().trim());
        ls.setLevelSkillValue(levelSkillEntity.getLevelSkillValue());
        ms004002UpdateIRepository.save(ls);
        return baseRes;
    }

}
