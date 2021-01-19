package com.brycen.hrm.masterservice.ms010002Create;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.PositionProjectEntity;

/**
 * [Description]: Interface create repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS010002CreateRepository extends JpaRepository<PositionProjectEntity, Long> {
    /**
     * [Description]: Find a project position with positionProjectCode<br/>
     * [ Remarks ]:<br/>
     *
     * @param positionProjectCode
     * @param companyID
     * @return a details project position
     */
    @Query(value = "SELECT * FROM position_project p WHERE p.position_project_code = :positionProjectCode AND p.company_id = :companyID", nativeQuery = true)
    PositionProjectEntity findPositionByPositionProjectCodeAndCompanyID(@Param("positionProjectCode") String positionProjectCode,
            @Param("companyID") int companyID);

}
