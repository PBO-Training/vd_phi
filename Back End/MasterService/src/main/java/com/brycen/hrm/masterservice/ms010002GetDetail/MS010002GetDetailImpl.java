package com.brycen.hrm.masterservice.ms010002GetDetail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.PositionProjectEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]:MS010001GetDetailImpl<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS010002GetDetailImpl implements MS010002GetDetailService {
    /**
     * Call to Repository and get methods to do actions Get detail project position
     */
    @Autowired
    private MS010002GetDetailRepository searchOneRepository;

    /**
     * Call to LoggerService and get methods to do actions Write log
     */
    @Autowired
    LoggerService logger;

    /**
     * [Description]:Validation<br/>
     * [ Remarks ]:<br/>
     *
     * @param positionProjectID
     * @param currentPosition
     * @return
     */
    public ErrorResponse validation(long positionProjectID, Optional<PositionProjectEntity> currentPosition) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        if (!currentPosition.isPresent()) {
            errorItemName.append("positionProjectID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_POSITION_PROJECT)
                    .append(ErrorValue.API_SEARCH_LIST_POSITION_PROJECT).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse getDetail(long positionProjectID, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<PositionProjectEntity> position = searchOneRepository.findBypositionProjectIDAndCompanyIDAndIsDelete(positionProjectID, companyID, false);
        ErrorResponse error = validation(positionProjectID, position);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS010002_GETDETAIL_POSITION_PROJECT, positionProjectID, baseRes, "");
            return baseRes;
        }
        PositionProjectEntity pos = position.get();
        MS010002GetDetailResponse searchOneRes = new MS010002GetDetailResponse();
        searchOneRes.setPositionProjectID(pos.getPositionProjectID());
        searchOneRes.setPositionProjectName(pos.getPositionProjectName());
        searchOneRes.setPositionProjectCode(pos.getPositionProjectCode());
        searchOneRes.setPositionProjectDescription(pos.getPositionProjectDescription());
        baseRes.setContent(searchOneRes);
        return baseRes;
    }
}
