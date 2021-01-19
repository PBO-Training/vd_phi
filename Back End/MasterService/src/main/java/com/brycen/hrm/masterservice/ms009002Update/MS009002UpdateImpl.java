package com.brycen.hrm.masterservice.ms009002Update;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.SqlValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.CustomerEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: This is place to update customer<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS009002UpdateImpl implements MS009002UpdateService {
    @Autowired
    private MS009002UpdateRepository updateRepository;

    @Autowired
    LoggerService logger;
    
    public ErrorResponse checkValue(CustomerEntity customerEntity, Optional<CustomerEntity> currentCustomer, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check duplicate code
        CustomerEntity customer = updateRepository.findCustomerByCustomerodeAndCompanyID(
                customerEntity.getCustomerCode().trim(), companyID, currentCustomer.get().getCustomerID());
        if (customer != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_CUSTOMER)
                    .append(ErrorValue.API_UPDATE_DETAIL_CUSTOMER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("customerCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        } else {
            // check null code
            if (CheckValueService.checkNull(customerEntity.getCustomerCode().trim())) {
                errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_CUSTOMER)
                        .append(ErrorValue.API_UPDATE_DETAIL_CUSTOMER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
                errorItemName.append("customerCode");
                error.setCode(errorItemCode.toString());
                error.setItemName(errorItemName.toString());
                return error;
            }
        }

        // Check name is null
        if (CheckValueService.checkNull(customerEntity.getCustomerName().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_CUSTOMER)
                    .append(ErrorValue.API_UPDATE_DETAIL_CUSTOMER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("customerName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        if (CheckValueService.checkMaxLength(customerEntity.getCustomerCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(customerEntity.getCustomerDescription().trim(), SqlValue.LENGTH_DESCRIPTION)
                || CheckValueService.checkMaxLength(customerEntity.getCustomerName().trim(), SqlValue.LENGTH_STRING)) {

            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_CUSTOMER)
                    .append(ErrorValue.API_UPDATE_DETAIL_CUSTOMER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

        }
        if (CheckValueService.checkMaxLength(customerEntity.getCustomerCode().trim(), SqlValue.LENGTH_CODE)) {
            errorItemName.append("customerCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(customerEntity.getCustomerDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
            errorItemName.append("customerDescription");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (CheckValueService.checkMaxLength(customerEntity.getCustomerName().trim(), SqlValue.LENGTH_STRING)) {
            errorItemName.append("CustomerName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        if (!currentCustomer.isPresent()) {
            errorItemName.append("customerID");
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_CUSTOMER)
                    .append(ErrorValue.API_UPDATE_DETAIL_CUSTOMER).append(ErrorValue.METHOD_PUT).append(ErrorValue.REASON_UNKNOWN_VALUE);
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(customerEntity.getCustomerCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_CUSTOMER)
            .append(ErrorValue.API_UPDATE_DETAIL_CUSTOMER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
            errorItemName.append("customerCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        return null;
    }

    @Override
    public BaseResponse save(CustomerEntity customerEntity, int companyID) {
        BaseResponse baseRes = new BaseResponse();
        Optional<CustomerEntity> currentCustomer = updateRepository.findByCustomerIDAndCompanyIDAndIsDelete(customerEntity.getCustomerID(), companyID, false);
        ErrorResponse error = checkValue(customerEntity, currentCustomer, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS009002_UPDATE_CUSTOMER, customerEntity, baseRes, "");
            return baseRes;
        }
        String customerCode = customerEntity.getCustomerCode().trim().toUpperCase();
        CustomerEntity customer = currentCustomer.get();
        customer.setIsDelete(customerEntity.getIsDelete());
        customer.setCustomerName(customerEntity.getCustomerName().trim());
        customer.setCustomerCode(customerCode);
        customer.setCustomerDescription(customerEntity.getCustomerDescription().trim());
        updateRepository.save(customer);
        return baseRes;
    }
}
