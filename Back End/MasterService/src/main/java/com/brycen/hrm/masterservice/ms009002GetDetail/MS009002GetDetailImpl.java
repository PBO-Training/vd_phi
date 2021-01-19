package com.brycen.hrm.masterservice.ms009002GetDetail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.CustomerEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]:MS009002GetDetailImpl<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS009002GetDetailImpl implements MS009002GetDetailService {
    @Autowired
    private MS009002GetDetailRepository detailRepository;
    
    @Autowired
    LoggerService logger;

    /**
     * [Description]:Validation<br/>
     * [ Remarks ]:<br/>
     *
     * @param customerID
     * @param currentCustomer
     * @return
     */
    public ErrorResponse validation(long customerID, Optional<CustomerEntity> currentCustomer) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        if (!currentCustomer.isPresent()) {
            errorItemName.append("customerID");
            errorItemCode
            .append(ErrorValue.TYPE_INPUT_VALUE_ERROR)
            .append(ErrorValue.SERVICE_API_MASTER)
            .append(ErrorValue.PACKAGE_CUSTOMER)
            .append(ErrorValue.API_SEARCH_LIST_CUSTOMER)
            .append(ErrorValue.METHOD_POST)
            .append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse getDetail(long customerID, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<CustomerEntity> currentCustomer = detailRepository.findByCustomerIDAndCompanyIDAndIsDelete(customerID, companyID, false);
        ErrorResponse error = validation(customerID, currentCustomer);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS009002_GETDETAIL_CUSTOMER, customerID, baseRes, "");
            return baseRes;
        }
        CustomerEntity customer = currentCustomer.get();
        MS009002GetDetailResponse searchOneRes = new MS009002GetDetailResponse();
        searchOneRes.setCustomerID(customer.getCustomerID());
        searchOneRes.setCustomerCode(customer.getCustomerCode());
        searchOneRes.setCustomerName(customer.getCustomerName());
        searchOneRes.setCustomerDescription(customer.getCustomerDescription());
        baseRes.setContent(searchOneRes);
        return baseRes;
    }
}
