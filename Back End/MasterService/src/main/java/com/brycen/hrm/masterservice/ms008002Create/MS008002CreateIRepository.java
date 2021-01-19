package com.brycen.hrm.masterservice.ms008002Create;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.StatusEmployeeEntity;

/**
 * [Description]: Create Repository for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS008002CreateIRepository extends JpaRepository<StatusEmployeeEntity, Long> {

    /**
     * [Description]: Find a status employee<br/>
     * [ Remarks ]:<br/>
     *
     * @param statusEmployeeCode
     * @param companyID
     * @return A status employee details
     */
    @Query(value = "SELECT * FROM status_employee se WHERE se.status_employee_code = :statusEmployeeCode AND se.company_id = :companyID", nativeQuery = true)
    StatusEmployeeEntity findStatusEmployeeByStatusEmployeeCodeAndCompanyID(@Param("statusEmployeeCode") String statusEmployeeCode, @Param("companyID") int companyID);
}
