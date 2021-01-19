package com.brycen.hrm.masterservice.ms013002Update;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
 * [Description]: This is place to update holiday<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS013002UpdateImpl implements MS013002UpdateService {
    @Autowired
    private MS013002UpdateRepository updateRepository;

    @Autowired
    private MS013002UpdateDetailHolidayRepository updateDetailRepository;

    @Autowired
    LoggerService logger;

    public ErrorResponse checkValue(MS013002UpdateRequest request, Optional<HolidayEntity> curentHoliday, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check holiday year < 0
        if (request.getHolidayYear() < 0) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_HOLIDAY)
                    .append(ErrorValue.API_UPDATE_DETAIL_HOLIDAY).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("holidayYear");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check duplicate holiday year
        HolidayEntity holiday = updateRepository.findHolidayByHolidayCodeAndCompanyID(request.getHolidayYear(), companyID, curentHoliday.get().getHolidayID());
        if (holiday != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_HOLIDAY)
                    .append(ErrorValue.API_UPDATE_DETAIL_HOLIDAY).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("holidayYear");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        if (request.getListHolidayDetails() != null) {
            for (int i = 0; i < request.getListHolidayDetails().size(); i++) {
                // check max lenghth holiday detail name
                if (CheckValueService.checkMaxLength(request.getListHolidayDetails().get(i).getHolidayDetailsName().trim(), SqlValue.LENGTH_STRING)) {
                    errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_HOLIDAY)
                            .append(ErrorValue.API_CREATE_DETAIL_HOLIDAY).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
                    errorItemName.append("holidayDetailsName");
                    error.setCode(errorItemCode.toString());
                    error.setItemName(errorItemName.toString());
                    return error;
                }

                Set<Date> store = new HashSet<Date>();
                for (MS013002UpdateDetailHolidayRequest holidayDetail : request.getListHolidayDetails()) {
                    if (store.add(holidayDetail.getHolidayDetailsDate()) == false) {
                        errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_HOLIDAY)
                                .append(ErrorValue.API_CREATE_DETAIL_HOLIDAY).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
                        errorItemName.append("holidayDetailsDate");
                        error.setCode(errorItemCode.toString());
                        error.setItemName(errorItemName.toString());
                        return error;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public BaseResponse save(MS013002UpdateRequest request, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<HolidayEntity> currentHoliday = updateRepository.findByHolidayIDAndCompanyIDAndIsDelete(request.getHolidayID(), companyID, false);
        ErrorResponse error = checkValue(request, currentHoliday, companyID);

        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS013002_CREATE_HOLIDAY, request, baseRes, "");
            return baseRes;
        }
        List<HolidayDetailEntity> listCurrentHoliday = updateDetailRepository.findHolidayDetailByHolidayIDAndCompanyID(request.getHolidayID(), companyID);
        for (int i = 0; i < listCurrentHoliday.size(); i++) {
            updateDetailRepository.delete(listCurrentHoliday.get(i));
        }
        // updateDetailRepository.DeleteByHolidayIDAndCompanyID(request.getHolidayID(), companyID);
        HolidayEntity holidayEntity = currentHoliday.get();
        for (int i = 0; i < request.getListHolidayDetails().size(); i++) {
            HolidayDetailEntity holidayDetails = new HolidayDetailEntity(request.getListHolidayDetails().get(i).getHolidayDetailsName().trim(),
                    request.getListHolidayDetails().get(i).getHolidayDetailsDate(), holidayEntity);
            holidayDetails.setCompanyID(companyID);
            updateDetailRepository.save(holidayDetails);
        }

        holidayEntity.setCompanyID(companyID);
        holidayEntity.setHolidayName(request.getHolidayName());
        holidayEntity.setHolidayYear(request.getHolidayYear());
        updateRepository.save(holidayEntity);
        return baseRes;
    }

}
