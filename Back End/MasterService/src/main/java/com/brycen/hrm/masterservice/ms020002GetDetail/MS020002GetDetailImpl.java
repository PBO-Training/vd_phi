package com.brycen.hrm.masterservice.ms020002GetDetail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.DegreeEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]:MS012001GetDetailImpl<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS020002GetDetailImpl implements MS020002GetDetailService {
    /**
     * Call to Repository and get methods to do actions Get detail project degree
     */
    @Autowired
    private MS020002GetDetailRepository searchOneRepository;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    LoggerService logger;

    /**
     * [Description]:Validation<br/>
     * [ Remarks ]:<br/>
     *
     * @param degreeID
     * @param currentdegree
     * @return
     */
    public ErrorResponse validation(long degreeID, Optional<DegreeEntity> currentdegree) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        if (!currentdegree.isPresent()) {
            errorItemName.append("degreeID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_DEGREE)
                    .append(ErrorValue.API_SEARCH_LIST_DEGREE).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse getDetail(long degreeID, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<DegreeEntity> degree = searchOneRepository.findBydegreeIDAndCompanyIDAndIsDelete(degreeID, companyID, false);
        ErrorResponse error = validation(degreeID, degree);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS020002_GETDETAIL_DEGREE, degreeID, baseRes, "");
            return baseRes;
        }
        DegreeEntity vac = degree.get();
        MS020002GetDetailResponse searchOneRes = new MS020002GetDetailResponse();
        searchOneRes.setDegreeID(vac.getDegreeID());
        searchOneRes.setDegreeName(vac.getDegreeName());
        searchOneRes.setDegreeCode(vac.getDegreeCode());
        searchOneRes.setDegreeDescription(vac.getDegreeDescription());
        baseRes.setContent(searchOneRes);
        return baseRes;
    }
}
