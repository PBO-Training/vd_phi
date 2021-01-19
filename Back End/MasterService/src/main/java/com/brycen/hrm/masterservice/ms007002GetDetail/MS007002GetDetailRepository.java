package com.brycen.hrm.masterservice.ms007002GetDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
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
public interface MS007002GetDetailRepository extends JpaRepository<PositionEmployeeEntity, Long> {
    /**
     * [Description]: Method find position by companyID and positionEmployeeID<br/>
     * [ Remarks ]:<br/>
     *
     * @param positionEmployeeID
     * @param companyID
     * @return PositionEmployee or error with errorName and errorCode
     */
    Optional<PositionEmployeeEntity> findByPositionEmployeeIDAndCompanyIDAndIsDelete(long positionEmployeeID, int companyID, boolean isDelete);
}
