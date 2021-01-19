package com.brycen.hrm.masterservice.ms008002Update;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.StatusEmployeeEntity;

/**
 * [Description]: Update Repository for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS008002UpdateIRepository extends JpaRepository<StatusEmployeeEntity, Long> {

    /**
     * [Description]: Find an employee status<br/>
     * [ Remarks ]:<br/>
     *
     * @param statusEmployeeID
     * @param companyID
     * @param isDelete
     * @return Employee status details
     */
    Optional<StatusEmployeeEntity> findByStatusEmployeeIDAndCompanyIDAndIsDelete(long statusEmployeeID, int companyID, boolean isDelete);

    /**
     * [Description]: Find an employee status<br/>
     * [ Remarks ]:<br/>
     *
     * @param statusEmployeeCode
     * @param companyID
     * @param statusEmployeeID
     * @return Employee status details
     */
    @Query(value = "SELECT * FROM status_employee se WHERE se.status_employee_code = :statusEmployeeCode AND se.company_id = :companyID AND se.status_employee_id <> :statusEmployeeID", nativeQuery = true)
    StatusEmployeeEntity findStatusEmployeeByStatusEmployeeCodeAndCompanyID(@Param("statusEmployeeCode") String statusEmployeeCode, @Param("companyID") int companyID, @Param("statusEmployeeID") Long statusEmployeeID);
}
