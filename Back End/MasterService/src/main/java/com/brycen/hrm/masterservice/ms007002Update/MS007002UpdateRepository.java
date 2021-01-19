package com.brycen.hrm.masterservice.ms007002Update;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.PositionEmployeeEntity;

/**
 * [Description]: Interface update repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS007002UpdateRepository extends JpaRepository<PositionEmployeeEntity, Long> {
    /**
     * [Description]: Method find position by companyID and positionEmployeeID<br/>
     * [ Remarks ]:<br/>
     *
     * @param positionEmployeeID
     * @param companyID
     * @param isDelete
     * @return Position or error with errorName and errorCode
     */
    Optional<PositionEmployeeEntity> findByPositionEmployeeIDAndCompanyIDAndIsDelete(long positionEmployeeID, int companyID, boolean isDelete);

    /**
     * [Description]: Find a position employee<br/>
     * [ Remarks ]:<br/>
     *
     * @param positionEmployeeCode
     * @param companyID
     * @param positionEmployeeID
     * @return A position employee details
     */
    @Query(value = "SELECT * FROM position_employee pe WHERE pe.position_employee_code = :positionEmployeeCode AND pe.company_id = :companyID AND pe.position_employee_id <> :positionEmployeeID", nativeQuery = true)
    PositionEmployeeEntity findPositionEmployeeByPositionEmployeeCodeAndCompanyID(@Param("positionEmployeeCode") String positionEmployeeCode, @Param("companyID") int companyID, @Param("positionEmployeeID") Long positionEmployeeID);
}
