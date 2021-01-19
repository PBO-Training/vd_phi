package com.brycen.hrm.masterservice.ms999001Init;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.DepartmentEntity;
import com.brycen.hrm.entity.EmployeeEntity;
import com.brycen.hrm.masterservice.ms999001Init.dto.MS999001InitDepartmentResponse;
import com.brycen.hrm.masterservice.ms999001Init.dto.MS999001InitEmployeeJoinInMonth;
import com.brycen.hrm.masterservice.ms999001Init.dto.MS999001InitResponse;

/**
 * [Description]:Class process description to init data for dashboard<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Component
public class MS999001InitProcess {

    /**
     * Use to create query for excute sql query
     */
    @Autowired
    private EntityManager em;

    /**
     * [Description]:Method process statistic employee for dashboard<br/>
     * [ Remarks ]:<br/>
     *
     * @param companyID
     * @return BaseResponse - Contain data to send to client
     */
    @SuppressWarnings({ "deprecation", "unchecked" })
    public BaseResponse initStatisticTotalEmployee(int companyID) {
        BaseResponse baseRes = new BaseResponse();
        MS999001InitResponse dashboardResponse = new MS999001InitResponse();
        StringBuffer selectCountEmployeeString = new StringBuffer("SELECT COUNT(E.employee_id) ");
        StringBuffer selectEmployeeString = new StringBuffer("SELECT * ");
        StringBuffer fromEmployeeString = new StringBuffer("FROM employee E ");
        StringBuffer whereEmployeeString = new StringBuffer("WHERE E.company_id= :companyID AND E.is_delete= 0 ");
        StringBuffer countEmployeeString = new StringBuffer();
        StringBuffer employeeString = new StringBuffer();

        StringBuffer selectDepartmentString = new StringBuffer("SELECT * ");
        StringBuffer fromDepartmentString = new StringBuffer("FROM department D ");
        StringBuffer whereDepartmentString = new StringBuffer("WHERE D.company_id= :companyID AND D.is_delete= 0 ");
        StringBuffer queryDepartmentString = new StringBuffer();

        countEmployeeString.append(selectCountEmployeeString).append(fromEmployeeString).append(whereEmployeeString);
        // Create query to get total employee
        Query queryCountEmployee = em.createNativeQuery(countEmployeeString.toString());
        queryCountEmployee.setParameter("companyID", companyID);
        int totalEmployee = ((Number) queryCountEmployee.getSingleResult()).intValue();

        // Add condition to whereEmployeeString
        whereEmployeeString.append("AND year(date_join_company) = year(now()) ");
        employeeString.append(selectEmployeeString).append(fromEmployeeString).append(whereEmployeeString);
        // Create query to get employee join company by date_join_company
        Query queryEmployee = em.createNativeQuery(employeeString.toString(), EmployeeEntity.class);
        queryEmployee.setParameter("companyID", companyID);

        queryDepartmentString.append(selectDepartmentString).append(fromDepartmentString).append(whereDepartmentString);
        // Create query to get department
        Query queryDepartment = em.createNativeQuery(queryDepartmentString.toString(), DepartmentEntity.class);
        queryDepartment.setParameter("companyID", companyID);

        List<EmployeeEntity> listEmployee = queryEmployee.getResultList();
        int numberEmpInJan = listEmployee.stream().filter(x -> x.getDateJoinCompany().getMonth() == 0).toArray().length;
        int numberEmpInFeb = listEmployee.stream().filter(x -> x.getDateJoinCompany().getMonth() == 1).toArray().length;
        int numberEmpInMar = listEmployee.stream().filter(x -> x.getDateJoinCompany().getMonth() == 2).toArray().length;
        int numberEmpInApr = listEmployee.stream().filter(x -> x.getDateJoinCompany().getMonth() == 3).toArray().length;
        int numberEmpInMay = listEmployee.stream().filter(x -> x.getDateJoinCompany().getMonth() == 4).toArray().length;
        int numberEmpInJu = listEmployee.stream().filter(x -> x.getDateJoinCompany().getMonth() == 5).toArray().length;
        int numberEmpInJul = listEmployee.stream().filter(x -> x.getDateJoinCompany().getMonth() == 6).toArray().length;
        int numberEmpInAug = listEmployee.stream().filter(x -> x.getDateJoinCompany().getMonth() == 7).toArray().length;
        int numberEmpInSep = listEmployee.stream().filter(x -> x.getDateJoinCompany().getMonth() == 8).toArray().length;
        int numberEmpInOct = listEmployee.stream().filter(x -> x.getDateJoinCompany().getMonth() == 9).toArray().length;
        int numberEmpInNov = listEmployee.stream().filter(x -> x.getDateJoinCompany().getMonth() == 10).toArray().length;
        int numberEmpInDec = listEmployee.stream().filter(x -> x.getDateJoinCompany().getMonth() == 11).toArray().length;
        MS999001InitEmployeeJoinInMonth empJoinInMonth = new MS999001InitEmployeeJoinInMonth();
        empJoinInMonth.setJanuary(numberEmpInJan);
        empJoinInMonth.setFebruary(numberEmpInFeb);
        empJoinInMonth.setMarch(numberEmpInMar);
        empJoinInMonth.setApril(numberEmpInApr);
        empJoinInMonth.setMay(numberEmpInMay);
        empJoinInMonth.setJune(numberEmpInJu);
        empJoinInMonth.setJuly(numberEmpInJul);
        empJoinInMonth.setAugust(numberEmpInAug);
        empJoinInMonth.setSeptember(numberEmpInSep);
        empJoinInMonth.setOctober(numberEmpInOct);
        empJoinInMonth.setNovember(numberEmpInNov);
        empJoinInMonth.setDecember(numberEmpInDec);

        List<DepartmentEntity> department = queryDepartment.getResultList();
        List<MS999001InitDepartmentResponse> result = department.stream().map(MS999001InitDepartmentResponse::new).collect(Collectors.toList());
        dashboardResponse.setDepartment(result);
        dashboardResponse.setTotalEmployee(totalEmployee);
        dashboardResponse.setEmpJoinInMonth(empJoinInMonth);
        baseRes.setContent(dashboardResponse);
        return baseRes;
    }
}
