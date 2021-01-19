package com.brycen.hrm.masterservice.ms010002Update;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.PositionProjectEntity;

/**
 * [Description]: Interface update repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS010002UpdateRepository extends JpaRepository<PositionProjectEntity, Long> {
    /**
     * [Description]: Method find project position by companyID and positionProjectID<br/>
     * [ Remarks ]:<br/>
     *
     * @param positionProjectID
     * @param companyID
     * @param isDelete
     * @return Position or error with errorName and errorCode
     */
    Optional<PositionProjectEntity> findByPositionProjectIDAndCompanyIDAndIsDelete(long positionProjectID, int companyID, boolean isDelete);

    /**
     * [Description]: Find a position details<br/>
     * [ Remarks ]:<br/>
     *
     * @param positionProjectCode
     * @param companyID
     * @param positionProjectID
     * @return a position details
     */
    @Query(value = "SELECT * FROM position_project p WHERE p.position_project_code = :positionProjectCode AND p.company_id = :companyID AND p.position_project_id <> :positionProjectID", nativeQuery = true)
    PositionProjectEntity findPositionProjectByPositionProjectCodeAndCompanyID(@Param("positionProjectCode") String positionProjectCode,
            @Param("companyID") int companyID, @Param("positionProjectID") Long positionProjectID);

}
