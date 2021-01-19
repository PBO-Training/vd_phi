package com.brycen.hrm.masterservice.ms010002GetDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
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
public interface MS010002GetDetailRepository extends JpaRepository<PositionProjectEntity, Long> {
    /**
     * [Description]: Method find project position by companyID and positionProjectID<br/>
     * [ Remarks ]:<br/>
     *
     * @param positionProjectID
     * @param companyID
     * @return Project position or error with errorName and errorCode
     */
    Optional<PositionProjectEntity> findBypositionProjectIDAndCompanyIDAndIsDelete(long positionProjectID, int companyID, boolean isDelete);
}
