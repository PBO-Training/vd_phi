package com.brycen.hrm.masterservice.ms006002Update;

import java.util.Optional;

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
 * [Description]: This is place to update level language<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS006002UpdateImpl implements MS006002UpdateService {
    /**
     * Call to Repository and get methods to do actions update level language
     */
    @Autowired
    private MS006002UpdateRepository updateRepository;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    private LoggerService logger;
    
    public ErrorResponse checkValue(LevelLanguageEntity levelLanguageEntity, Optional<LevelLanguageEntity> currentLanguage, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check duplicate code
        LevelLanguageEntity levelLanguage = updateRepository.findLevelLanguageByLevelLanguageCodeAndCompanyID(levelLanguageEntity.getLevelLanguageCode().trim(), companyID,
                currentLanguage.get().getLevelLanguageID());
        if (levelLanguage != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LEVEL_LANGUAGE)
                    .append(ErrorValue.API_UPDATE_DETAIL_LEVEL_LANGUAGE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("levelLanguageCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        } else {
            // check null code
            if (CheckValueService.checkNull(levelLanguageEntity.getLevelLanguageCode().trim())) {
                errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LEVEL_LANGUAGE)
                        .append(ErrorValue.API_UPDATE_DETAIL_LEVEL_LANGUAGE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
                errorItemName.append("levelLanguageCode");
                error.setCode(errorItemCode.toString());
                error.setItemName(errorItemName.toString());
                return error;
            }
        }

     // Check name is null
        if (CheckValueService.checkNull(levelLanguageEntity.getLevelLanguageName().trim())) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_LEVEL_LANGUAGE)
            .append(ErrorValue.API_UPDATE_DETAIL_LEVEL_LANGUAGE)
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

            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LANGUAGE)
                    .append(ErrorValue.API_UPDATE_DETAIL_LANGUAGE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
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
        if (!currentLanguage.isPresent()) {
            errorItemName.append("levelLanguageID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LANGUAGE)
                    .append(ErrorValue.API_UPDATE_DETAIL_LANGUAGE).append(ErrorValue.METHOD_PUT).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(levelLanguageEntity.getLevelLanguageCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LANGUAGE)
            .append(ErrorValue.API_UPDATE_DETAIL_LANGUAGE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("levelLanguageCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    /**
     * [Description]: Method find a level language with id specification and update it<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return BaseResponse - Model contain data what need to send to client
     */
    @Override
    public BaseResponse save(LevelLanguageEntity levelLanguageEntity, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<LevelLanguageEntity> currentLanguage = updateRepository.findById(levelLanguageEntity.getLevelLanguageID());
        ErrorResponse error = checkValue(levelLanguageEntity, currentLanguage, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS006002_UPDATE_LEVEL_LANGUAGE, levelLanguageEntity, baseRes, "");
            return baseRes;
        }
        String languageCode = levelLanguageEntity.getLevelLanguageCode().trim().toUpperCase();
        LevelLanguageEntity lang = currentLanguage.get();
        lang.setLevelLanguageName(levelLanguageEntity.getLevelLanguageName().trim());
        lang.setIsDelete(levelLanguageEntity.getIsDelete());
        lang.setLevelLanguageCode(languageCode);
        lang.setLevelLanguageDescription(levelLanguageEntity.getLevelLanguageDescription().trim());
        lang.setLevelLanguageValue(levelLanguageEntity.getLevelLanguageValue());
        updateRepository.save(lang);
        return baseRes;
    }
}
