package com.brycen.hrm.masterservice.ms004002Create;

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
 * [Description]: Create Service Implementation for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS004002CreateImpl implements MS004002CreateIService {

    @Autowired
    private MS004002CreateIRepository ms004002CreateIRepository;
    
    @Autowired
    LoggerService logger;
    
    public ErrorResponse checkValue(LevelSkillEntity levelSkillEntity, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check code is null
        if (CheckValueService.checkNull(levelSkillEntity.getLevelSkillCode().trim())) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_SKILL_LEVEL)
            .append(ErrorValue.API_CREATE_DETAIL_LEVEL_SKILL)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("levelSkillCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check code is duplicate
        LevelSkillEntity levelSkill = ms004002CreateIRepository.findLevelSkillByLevelSkillCodeAndCompanyID(levelSkillEntity.getLevelSkillCode().trim(), companyID);
        if (levelSkill != null) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_SKILL_LEVEL)
            .append(ErrorValue.API_CREATE_DETAIL_LEVEL_SKILL)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("levelSkillCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check name is null
        if (CheckValueService.checkNull(levelSkillEntity.getLevelSkillName().trim())) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_SKILL_LEVEL)
            .append(ErrorValue.API_CREATE_DETAIL_LEVEL_SKILL)
            .append(ErrorValue.METHOD_POST)
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
                    .append(ErrorValue.API_CREATE_DETAIL_LEVEL_SKILL).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

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
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(levelSkillEntity.getLevelSkillCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SKILL_LEVEL)
            .append(ErrorValue.API_CREATE_DETAIL_LEVEL_SKILL).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
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
        ErrorResponse error = checkValue(levelSkillEntity, companyID);

        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS004002_CREATE_SKILL_LEVEL, levelSkillEntity, baseRes, "");
            return baseRes;
        }

        String levelSkillCode = levelSkillEntity.getLevelSkillCode().trim().toUpperCase();
        levelSkillEntity.setCompanyID(companyID);
        levelSkillEntity.setLevelSkillCode(levelSkillCode);
        String levelSkillName = levelSkillEntity.getLevelSkillName().trim();
        levelSkillEntity.setLevelSkillName(levelSkillName);
        String levelSkillDescription = levelSkillEntity.getLevelSkillDescription().trim();
        levelSkillEntity.setLevelSkillDescription(levelSkillDescription);
        int levelSkillValue = levelSkillEntity.getLevelSkillValue();
        levelSkillEntity.setLevelSkillValue(levelSkillValue);
        ms004002CreateIRepository.save(levelSkillEntity);
        return baseRes;
    }

}
