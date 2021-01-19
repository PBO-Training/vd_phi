package com.brycen.hrm.masterservice.ms007002Create;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.PositionEmployeeEntity;

/**
 * [Description]: Interface create repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS007002CreateRepository extends JpaRepository<PositionEmployeeEntity, Long> {
    /**
     * [Description]: Find a position employee<br/>
     * [ Remarks ]:<br/>
     *
     * @param positionEmployeeCode
     * @param companyID
     * @return A position employee details
     */
    @Query(value = "SELECT * FROM position_employee pe WHERE pe.position_employee_code = :positionEmployeeCode AND pe.company_id = :companyID", nativeQuery = true)
    PositionEmployeeEntity findPositionEmployeeByPositionEmployeeCodeAndCompanyID(@Param("positionEmployeeCode") String positionEmployeeCode, @Param("companyID") int companyID);
}
