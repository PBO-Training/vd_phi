package com.brycen.hrm.masterservice.ms001002Update;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.EmployeeEntity;

/**
 * [Description]: Employee Repository for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS001002UpdateIEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    /**
     * [Description]: Find an employee by employeeID<br/>
     * [ Remarks ]:<br/>
     *
     * @param employeeID
     * @param companyID
     * @param isDelete
     * @return An employee details
     */
    Optional<EmployeeEntity> findByEmployeeIDAndCompanyIDAndIsDelete(long employeeID, int companyID, boolean isDelete);
}
