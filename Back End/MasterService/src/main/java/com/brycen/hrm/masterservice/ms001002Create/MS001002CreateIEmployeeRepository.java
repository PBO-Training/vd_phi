package com.brycen.hrm.masterservice.ms001002Create;

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
public interface MS001002CreateIEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    /**
     * [Description]: Find employee to corresponds employeeID and companyID and isDelete<br/>
     * [ Remarks ]:<br/>
     *
     * @param employeeID
     * @param companyID
     * @param isDelete
     * @return Employee to corresponds employeeID
     */
    Optional<EmployeeEntity> findByEmployeeIDAndCompanyIDAndIsDelete(long employeeID, int companyID, boolean isDelete);

}
