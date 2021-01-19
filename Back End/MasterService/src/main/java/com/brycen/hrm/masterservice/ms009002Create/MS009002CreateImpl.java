package com.brycen.hrm.masterservice.ms009002Create;

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
 * [Description]: This is place make to create new customer<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS009002CreateImpl implements MS009002CreateService {
    @Autowired
    private MS009002CreateRepository createRepository;

    @Autowired
    private LoggerService logger;
    
    public ErrorResponse checkValue(CustomerEntity customerEntity, int companyID) {
        ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();

        // Check code is null
        if (CheckValueService.checkNull(customerEntity.getCustomerCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_CUSTOMER)
                    .append(ErrorValue.API_CREATE_DETAIL_CUSTOMER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("customerCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check code is duplicate
        CustomerEntity customer = createRepository
                .findCustomerByCustomerCodeAndCompanyID(customerEntity.getCustomerCode().trim(), companyID);
        if (customer != null) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_CUSTOMER)
                    .append(ErrorValue.API_CREATE_DETAIL_CUSTOMER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
            errorItemName.append("customerCode");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        // Check name is null
        if (CheckValueService.checkNull(customerEntity.getCustomerName().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_CUSTOMER)
                    .append(ErrorValue.API_CREATE_DETAIL_CUSTOMER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
            errorItemName.append("customerName");
            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }

        if (CheckValueService.checkMaxLength(customerEntity.getCustomerCode().trim(), SqlValue.LENGTH_CODE)
                || CheckValueService.checkMaxLength(customerEntity.getCustomerDescription().trim(), SqlValue.LENGTH_DESCRIPTION)
                || CheckValueService.checkMaxLength(customerEntity.getCustomerName().trim(), SqlValue.LENGTH_STRING)) {

            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_CUSTOMER)
                    .append(ErrorValue.API_CREATE_DETAIL_CUSTOMER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

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
            errorItemName.append("customerName");

            error.setCode(errorItemCode.toString());
            error.setItemName(errorItemName.toString());
            return error;
        }
        // check 'space' and special symbol for field code
        if (CheckValueService.isAlphaNumber(customerEntity.getCustomerCode().trim())) {
            errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_CUSTOMER)
            .append(ErrorValue.API_CREATE_DETAIL_CUSTOMER).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
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
        ErrorResponse error = checkValue(customerEntity, companyID);
        if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS009002_CREATE_CUSTOMER, customerEntity, baseRes, "");
            return baseRes;
        }
        String customerCode = customerEntity.getCustomerCode().trim().toUpperCase();
        customerEntity.setCompanyID(companyID);
        customerEntity.setCustomerCode(customerCode);
        String customerName = customerEntity.getCustomerName().trim();
        customerEntity.setCustomerName(customerName);
        String customerDescription = customerEntity.getCustomerDescription().trim();
        customerEntity.setCustomerDescription(customerDescription);
        createRepository.save(customerEntity);
        return baseRes;
    }
}
