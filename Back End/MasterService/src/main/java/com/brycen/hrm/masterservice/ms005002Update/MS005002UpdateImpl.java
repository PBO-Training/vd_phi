package com.brycen.hrm.masterservice.ms005002Update;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.SqlValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.LanguageEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: This is place to update language<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS005002UpdateImpl implements MS005002UpdateService {
    /**
     * Call to Repository and get methods to do actions create new language
     */
    @Autowired
    private MS005002UpdateRepository updateRepository;

    @Autowired
    private LoggerService logger;
    
    public ErrorResponse checkValue(LanguageEntity languageEntity, Optional<LanguageEntity> currentLanguage, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check duplicate code
        LanguageEntity language = updateRepository.findLanguageByLanguageCodeAndCompanyID(languageEntity.getLanguageCode().trim(), companyID,
                currentLanguage.get().getLanguageID());
        if (language != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LANGUAGE)
                    .append(ErrorValue.API_UPDATE_DETAIL_LANGUAGE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("languageCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        } else {
            // check null code
            if (CheckValueService.checkNull(languageEntity.getLanguageCode().trim())) {
                errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LANGUAGE)
                        .append(ErrorValue.API_UPDATE_DETAIL_LANGUAGE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
                errorItemName.append("languageCode");
                error.setCode(errorItemCode.toString());
                error.setItemName(errorItemName.toString());
                return error;
            }
        }

        // Check name is null
        if (CheckValueService.checkNull(languageEntity.getLanguageName().trim())) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_LANGUAGE)
            .append(ErrorValue.API_UPDATE_DETAIL_LANGUAGE)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("languageName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        if (CheckValueService.checkMaxLength(languageEntity.getLanguageCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(languageEntity.getLanguageDescription().trim(), SqlValue.LENGTH_DESCRIPTION)
                || CheckValueService.checkMaxLength(languageEntity.getLanguageName().trim(), SqlValue.LENGTH_STRING)) {

            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LANGUAGE)
                    .append(ErrorValue.API_UPDATE_DETAIL_LANGUAGE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

        }
        if (CheckValueService.checkMaxLength(languageEntity.getLanguageCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("languageCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(languageEntity.getLanguageDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemName.append("languageDescription");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(languageEntity.getLanguageName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("languageName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (!currentLanguage.isPresent()) {
            errorItemName.append("languageID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LANGUAGE)
                    .append(ErrorValue.API_UPDATE_DETAIL_LANGUAGE).append(ErrorValue.METHOD_PUT).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(languageEntity.getLanguageCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LANGUAGE)
            .append(ErrorValue.API_UPDATE_DETAIL_LANGUAGE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("languageCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    /**
     * [Description]: Method find a language with id specification and update it<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return MS005002UpdateResponse - Model contain data what need to send to client
     */
    @Override
    public BaseResponse save(LanguageEntity languageEntity, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<LanguageEntity> currentLanguage = updateRepository.findById(languageEntity.getLanguageID());
        ErrorResponse error = checkValue(languageEntity, currentLanguage, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS005002_UPDATE_LANGUAGE, languageEntity, baseRes, "");
            return baseRes;
        }
        String languageCode = languageEntity.getLanguageCode().trim().toUpperCase();
        LanguageEntity lang = currentLanguage.get();
        lang.setLanguageName(languageEntity.getLanguageName().trim());
        lang.setIsDelete(languageEntity.getIsDelete());
        lang.setLanguageCode(languageCode);
        lang.setLanguageDescription(languageEntity.getLanguageDescription().trim());
        updateRepository.save(lang);
        return baseRes;
    }

}
