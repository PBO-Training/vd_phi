package com.brycen.hrm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.EmployeeEntity;

/**
 * [Description]: Employee Repository<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface EmployeeIRepository extends JpaRepository<EmployeeEntity, Long> {

    /**
     * [Description]: Find employee to corresponds employeeID and companyID<br/>
     * [ Remarks ]:<br/>
     *
     * @param employeeID
     * @param companyID
     * @return Employee to corresponds employeeID
     */
    Optional<EmployeeEntity> findByEmployeeIDAndCompanyID(long employeeID, int companyID);

}
