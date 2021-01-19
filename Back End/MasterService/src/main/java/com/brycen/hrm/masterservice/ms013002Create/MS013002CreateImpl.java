package com.brycen.hrm.masterservice.ms013002Create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.SqlValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.HolidayDetailEntity;
import com.brycen.hrm.entity.HolidayEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Service Implementation for Holiday Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS013002CreateImpl implements MS013002CreateService {

    /**
     * Call create repository to create a new holiday
     */
    @Autowired
    private MS013002CreateRepository createRepository;

    /**
     * Call create details repository to create a list holiday details
     */
    @Autowired
    private MS013002CreateHolidayDetailsIRepository ms013002CreateHolidayDetailsIRepository;

    /**
     * Write log
     */
    @Autowired
    LoggerService logger;

    public ErrorResponse checkValue(MS013002CreateRequest ms013002CreateRequest, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        
        //check maxlenghth holiday name 
        if (CheckValueService.checkMaxLength(ms013002CreateRequest.getHolidayName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_HOLIDAY)
                    .append(ErrorValue.API_CREATE_DETAIL_HOLIDAY).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
            errorItemName.append("holidayName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check holiday year < 0
        if (ms013002CreateRequest.getHolidayYear() < 0) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_HOLIDAY)
                    .append(ErrorValue.API_CREATE_DETAIL_HOLIDAY).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("holidayYear");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check holiday year is duplicate
        HolidayEntity holiday = createRepository.findHolidayByHolidayCodeAndCompanyID(ms013002CreateRequest.getHolidayYear(), companyID);
        if (holiday != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_HOLIDAY)
                    .append(ErrorValue.API_CREATE_DETAIL_HOLIDAY).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("holidayYear");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        if (ms013002CreateRequest.getListHolidayDetails() != null) {
            for (int i = 0; i < ms013002CreateRequest.getListHolidayDetails().size(); i++) {
                // check null date
                if (ms013002CreateRequest.getListHolidayDetails().get(i).getHolidayDetailsDate() == null) {
                    errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_HOLIDAY)
                            .append(ErrorValue.API_CREATE_DETAIL_HOLIDAY).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
                    errorItemName.append("holidayDetailDate");
                    error.setCode(errorItemCode.toString());
                    error.setItemName(errorItemName.toString());
                    return error;
                }
                // check max lenghth holiday detail name
                if (CheckValueService.checkMaxLength(ms013002CreateRequest.getListHolidayDetails().get(i).getHolidayDetailsName().trim(),
                        SqlValue.LENGTH_STRING)) {
                    errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_HOLIDAY)
                            .append(ErrorValue.API_CREATE_DETAIL_HOLIDAY).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
                    errorItemName.append("holidayDetailName");
                    error.setCode(errorItemCode.toString());
                    error.setItemName(errorItemName.toString());
                    return error;
                }
            }
        }

        return null;
    }

    @Override
    public BaseResponse save(MS013002CreateRequest ms013002CreateRequest, int companyID) {

        BaseResponse baseRes = new BaseResponse();
        HolidayEntity holiday = new HolidayEntity();
        ErrorResponse error = checkValue(ms013002CreateRequest, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS013002_CREATE_HOLIDAY, ms013002CreateRequest, baseRes, "");
            return baseRes;
        }
        holiday.setCompanyID(companyID);
        holiday.setHolidayName(ms013002CreateRequest.getHolidayName().trim());
        holiday.setHolidayYear(ms013002CreateRequest.getHolidayYear());
        createRepository.save(holiday);
        if (ms013002CreateRequest.getListHolidayDetails() != null) {
            for (int i = 0; i < ms013002CreateRequest.getListHolidayDetails().size(); i++) {
                HolidayDetailEntity holidayDetails = new HolidayDetailEntity(
                        ms013002CreateRequest.getListHolidayDetails().get(i).getHolidayDetailsName().trim(),
                        ms013002CreateRequest.getListHolidayDetails().get(i).getHolidayDetailsDate(), holiday);
                holidayDetails.setCompanyID(companyID);
                ms013002CreateHolidayDetailsIRepository.save(holidayDetails);
            }
        }
        return baseRes;
    }

}
