package com.brycen.hrm.masterservice.ms006002GetDetail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.LevelLanguageEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]:MS006002GetDetailImpl<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS006002GetDetailImpl implements MS006002GetDetailService {
    /**
     * Call to Repository and get methods to do actions Get detail level language
     */
    @Autowired
    private MS006002GetDetailRepository searchOneRepository;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    private LoggerService logger;

    /**
     * [Description]:Validation<br/>
     * [ Remarks ]:<br/>
     *
     * @param levelLanguageID
     * @param currentLanguage
     * @return
     */
    public ErrorResponse validation(long levelLanguageID, Optional<LevelLanguageEntity> currentLanguage) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        if (!currentLanguage.isPresent()) {
            errorItemName.append("levelLanguageID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_LEVEL_LANGUAGE)
                    .append(ErrorValue.API_SEARCH_LIST_LEVEL_LANGUAGE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse getDetail(long levelLanguageID, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<LevelLanguageEntity> levelLanguage = searchOneRepository.findByLevelLanguageIDAndCompanyIDAndIsDelete(levelLanguageID, companyID, false);
        ErrorResponse error = validation(levelLanguageID, levelLanguage);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS006002_GETDETAIL_LEVEL_LANGUAGE, levelLanguage, baseRes, "");
            return baseRes;
        }
        LevelLanguageEntity levelLang = levelLanguage.get();
        MS006002GetDetailResponse searchOneRes = new MS006002GetDetailResponse();
        searchOneRes.setLevelLanguageID(levelLang.getLevelLanguageID());
        searchOneRes.setLevelLanguageName(levelLang.getLevelLanguageName());
        searchOneRes.setLevelLanguageCode(levelLang.getLevelLanguageCode());
        searchOneRes.setLevelLanguageDescription(levelLang.getLevelLanguageDescription());
        searchOneRes.setLevelLanguageValue(levelLang.getLevelLanguageValue());
        baseRes.setContent(searchOneRes);
        return baseRes;
    }
}
