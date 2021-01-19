package com.brycen.hrm.masterservice.ms005002Create;

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
 * [Description]: This is place make to create new language<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS005002CreateImpl implements MS005002CreateService {
    /**
     * Call to Repository and get methods to do actions create new language
     */
    @Autowired
    private MS005002CreateRepository createRepository;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */ 
    @Autowired
    private LoggerService logger;
    
    public ErrorResponse checkValue(LanguageEntity languageEntity, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check code is null
        if (CheckValueService.checkNull(languageEntity.getLanguageCode().trim())) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_LANGUAGE)
            .append(ErrorValue.API_CREATE_DETAIL_LANGUAGE)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("languageCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check code is duplicate
        LanguageEntity language = createRepository.findLanguageByLanguageCodeAndCompanyID(languageEntity.getLanguageCode().trim(), companyID);
        if (language != null) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_LANGUAGE)
            .append(ErrorValue.API_CREATE_DETAIL_LANGUAGE)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("languageCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check name is null
        if (CheckValueService.checkNull(languageEntity.getLanguageName().trim())) {
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_LANGUAGE)
            .append(ErrorValue.API_CREATE_DETAIL_LANGUAGE)
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
                    .append(ErrorValue.API_CREATE_DETAIL_LANGUAGE).append(ErrorValue.METHOD_POST);

        }
        if (CheckValueService.checkMaxLength(languageEntity.getLanguageCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("languageCode");
            errorItemCode.append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(languageEntity.getLanguageDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemName.append("languageDescription");
            errorItemCode.append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(languageEntity.getLanguageName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("languageName");
            errorItemCode.append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(languageEntity.getLanguageCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LANGUAGE)
            .append(ErrorValue.API_CREATE_DETAIL_LANGUAGE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("languageCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    /**
     * [Description]: Method create new language<br/>
     * [ Remarks ]:<br/>
     *
     * @param languageEntity
     */
    @Override
    public BaseResponse save(LanguageEntity languageEntity, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        ErrorResponse error = checkValue(languageEntity, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS005002_CREATE_LANGUAGE, languageEntity, baseRes, "");
            return baseRes;
        }
        String languageCode = languageEntity.getLanguageCode().trim().toUpperCase();
        languageEntity.setCompanyID(companyID);
        languageEntity.setLanguageCode(languageCode);
        String languageName = languageEntity.getLanguageName().trim();
        languageEntity.setLanguageName(languageName);
        String languageDescription = languageEntity.getLanguageDescription().trim();
        languageEntity.setLanguageDescription(languageDescription);
        createRepository.save(languageEntity);
        return baseRes;
    }

}
