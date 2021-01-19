package com.brycen.hrm.masterservice.ms006002Create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.SqlValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.LevelLanguageEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: This is place make to create new level language<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS006002CreateImpl implements MS006002CreateService {
    /**
     * Call to Repository and get methods to do actions Create new level language
     */
    @Autowired
    private MS006002CreateRepository createRepository;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    private LoggerService logger;
    
    public ErrorResponse checkValue(LevelLanguageEntity levelLanguageEntity, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check code is null
        if (CheckValueService.checkNull(levelLanguageEntity.getLevelLanguageCode().trim())) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_LEVEL_LANGUAGE)
            .append(ErrorValue.API_CREATE_DETAIL_LEVEL_LANGUAGE)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("levelLanguageCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check code is duplicate
        LevelLanguageEntity levelLanguage = createRepository.findLevelLanguageByLevelLanguageCodeAndCompanyID(levelLanguageEntity.getLevelLanguageCode().trim(), companyID);
        if (levelLanguage != null) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_LEVEL_LANGUAGE)
            .append(ErrorValue.API_CREATE_DETAIL_LEVEL_LANGUAGE)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("levelLanguageCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check name is null
        if (CheckValueService.checkNull(levelLanguageEntity.getLevelLanguageName().trim())) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_LEVEL_LANGUAGE)
            .append(ErrorValue.API_CREATE_DETAIL_LEVEL_LANGUAGE)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("levelLanguageName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        if (CheckValueService.checkMaxLength(levelLanguageEntity.getLevelLanguageCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(levelLanguageEntity.getLevelLanguageDescription().trim(), SqlValue.LENGTH_DESCRIPTION)
                || CheckValueService.checkMaxLength(levelLanguageEntity.getLevelLanguageName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LEVEL_LANGUAGE)
                    .append(ErrorValue.API_CREATE_DETAIL_LEVEL_LANGUAGE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
        }
        if (CheckValueService.checkMaxLength(levelLanguageEntity.getLevelLanguageCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("levelLanguageCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(levelLanguageEntity.getLevelLanguageDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemName.append("levelLanguageDescription");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(levelLanguageEntity.getLevelLanguageName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("levelLanguageName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(levelLanguageEntity.getLevelLanguageCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LANGUAGE)
            .append(ErrorValue.API_CREATE_DETAIL_LANGUAGE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("levelLanguageCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse save(LevelLanguageEntity levelLanguageEntity, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        ErrorResponse error = checkValue(levelLanguageEntity, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS006002_CREATE_LEVEL_LANGUAGE, levelLanguageEntity, baseRes, "");
            return baseRes;
        }
        String levelLanguageCode = levelLanguageEntity.getLevelLanguageCode().trim().toUpperCase();
        levelLanguageEntity.setCompanyID(companyID);
        levelLanguageEntity.setLevelLanguageCode(levelLanguageCode);
        String levelLanguageName = levelLanguageEntity.getLevelLanguageName().trim();
        levelLanguageEntity.setLevelLanguageName(levelLanguageName);
        String levelLanguageDescription = levelLanguageEntity.getLevelLanguageDescription().trim();
        levelLanguageEntity.setLevelLanguageDescription(levelLanguageDescription);
        int levelLanguageValue = levelLanguageEntity.getLevelLanguageValue();
        levelLanguageEntity.setLevelLanguageValue(levelLanguageValue);
        createRepository.save(levelLanguageEntity);
        return baseRes;
    }
}
