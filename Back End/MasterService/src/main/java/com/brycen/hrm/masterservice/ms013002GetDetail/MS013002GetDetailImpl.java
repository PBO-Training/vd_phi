package com.brycen.hrm.masterservice.ms013002GetDetail;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.HolidayEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]:MS013002GetDetailImpl<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS013002GetDetailImpl implements MS013002GetDetailService {

    @Autowired
    private MS013002GetDetailRepository searchOneRepository;

    @Autowired
    LoggerService logger;

    /**
     * [Description]:Validation<br/>
     * [ Remarks ]:<br/>
     *
     * @param holidayID
     * @param currentHoliday
     * @return
     */
    public ErrorResponse validation(long holidayID, Optional<HolidayEntity> currentHoliday) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        if (!currentHoliday.isPresent()) {
            errorItemName.append("holidayID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_VACTION)
                    .append(ErrorValue.API_SEARCH_LIST_VACATION).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse getDetail(long holidayID, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        List<HolidayEntity> holiday = searchOneRepository.findHolidayByHolidayIDAndCompanyIDAndIsDelete(holidayID, companyID, false);
        Optional<HolidayEntity> oneHoliday = searchOneRepository.findOneHolidayByHolidayIDAndCompanyIDAndIsDelete(holidayID, companyID, false);
        ErrorResponse error = validation(holidayID, oneHoliday);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS013002_GETDETAIL_HOLIDAY, holidayID, baseRes, "");
            return baseRes;
        }
        List<Object> data = holiday.stream().map(MS013002GetDetailResponse::new).collect(Collectors.toList());
        baseRes.setContent(data.get(0));
        return baseRes;
    }
}
