package com.brycen.hrm.masterservice.ms005002GetDetail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.LanguageEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]:MS005001GetDetailImpl<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS005002GetDetailImpl implements MS005002GetDetailService {
    /**
     * Call to Repository and get methods to do actions Get detail language
     */
    @Autowired
    private MS005002GetDetailRepository searchOneRepository;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]:Validation<br/>
     * [ Remarks ]:<br/>
     *
     * @param languageID
     * @param currentLanguage
     * @return
     */
    public ErrorResponse validation(long languageID, Optional<LanguageEntity> currentLanguage) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        if (!currentLanguage.isPresent()) {
            errorItemName.append("languageID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LANGUAGE)
                    .append(ErrorValue.API_SEARCH_LIST_LANGUAGE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse getDetail(long languageID, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<LanguageEntity> language = searchOneRepository.findByLanguageIDAndCompanyIDAndIsDelete(languageID, companyID, false);
        ErrorResponse error = validation(languageID, language);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS005002_GETDETAIL_LANGUAGE, languageID, baseRes, "");
            return baseRes;
        }
        LanguageEntity lang = language.get();
        MS005002GetDetailResponse searchOneRes = new MS005002GetDetailResponse();
        searchOneRes.setLanguageID(lang.getLanguageID());
        searchOneRes.setLanguageName(lang.getLanguageName());
        searchOneRes.setLanguageCode(lang.getLanguageCode());
        searchOneRes.setLanguageDescription(lang.getLanguageDescription());
        baseRes.setContent(searchOneRes);
        return baseRes;
    }
}
