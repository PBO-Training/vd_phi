package com.brycen.hrm.masterservice.ms001002CreateDefaultEmp;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.gerenateErrorCode.ErrorCodeInterface;
import com.brycen.hrm.common.gerenateErrorCode.ErrorCodeObj;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.EmployeeEntity;
import com.brycen.hrm.logger.ILogger;
import com.brycen.hrm.logger.LogLevel;

/**
 * [Description]:Service Impl of API Create Default Employee<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS001002CreateDefaultEmpImplement implements MS001002CreateDefaultEmpService{
    @Autowired
    private MS001002CreateDefaultEmpRepository repo;
    
    @Autowired
    private ILogger iLogger;
    
    @Autowired
    private ErrorCodeInterface errorCodeInterface;
    
    // generate random number as string
    public String generateNumber(int length) {
        Random random = new Random();
        String number = random.ints(48, 57 + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        return number;
    }
    
    @Override
    public BaseResponse em001002CreateDefault(int companyID) {
        BaseResponse response = new BaseResponse();
        ErrorCodeObj error = new ErrorCodeObj();
        String empCode = generateNumber(3);
        String companyCode = repo.findCompanyCodeByCompanyIDAndIsDelete(companyID, false);
        List<EmployeeEntity> currentEntity = repo.findEmployeeByEmployeeCodeAndCompanyIDAndIsDelete(empCode, companyID, false);
        while (currentEntity.size() != 0) {
            empCode = generateNumber(3);
            currentEntity = repo.findEmployeeByEmployeeCodeAndCompanyIDAndIsDelete(empCode, companyID, false);
        }
        List<Long> listEmpID = repo.findAllEmployeeID(companyID, false);
        List<Long> listEmpIDAtUser  = repo.findAllEmployeeIDAtUser(companyID, false);
        if (!listEmpIDAtUser.containsAll(listEmpID)) {
            error = new ErrorCodeObj(ErrorValue.TYPE_INPUT_VALUE_ERROR, ErrorValue.SERVICE_API_MASTER, ErrorValue.PACKAGE_EMPLOYEE,
                    ErrorValue.API_CREATE_DETAIL_EMPLOYEE, ErrorValue.METHOD_POST, ErrorValue.REASON_VALUE_ILLEGAL);
            String ErrorCode = errorCodeInterface.generateErrorCode(error);
            response.setError(new ErrorResponse(ErrorCode, "api generate employee"));
            iLogger.write(LogLevel.ERROR, UrlAPI.MS001002_CREATE_DEFAULT, null, null, "have data unuse");
            return response;
        }
        //TODO: validate empCode is full

        EmployeeEntity employee = new EmployeeEntity();
        employee.setCompanyID(companyID);
        employee.setIsDelete(false);
        employee.setEmployeeCode(companyCode.toUpperCase() + empCode);
        employee.setFirstName("Employee");
        employee.setLastName(generateNumber(3));
        employee.setGender(true);
        employee.setEmail(empCode + "@gmail.com");
        employee.setIdentityCard("null");
        employee.setDateJoinCompany(new Date());
        employee.setPhone("null");

        this.repo.save(employee);
        
        MS001002CreateDefaultEmpResponse employeeResponse = new MS001002CreateDefaultEmpResponse(employee);
        response.setContent(employeeResponse);
        return response;
    }

}
