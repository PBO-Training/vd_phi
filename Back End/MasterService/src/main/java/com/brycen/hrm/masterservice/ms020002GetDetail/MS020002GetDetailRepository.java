package com.brycen.hrm.masterservice.ms020002GetDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.DegreeEntity;

/**
 * [Description]: Interface update repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS020002GetDetailRepository extends JpaRepository<DegreeEntity, Long> {
    /**
     * [Description]: Method find degree by companyID and degreeID<br/>
     * [ Remarks ]:<br/>
     *
     * @param degreeID
     * @param companyID
     * @return degree or error with errorName and errorCode
     */
    Optional<DegreeEntity> findBydegreeIDAndCompanyIDAndIsDelete(long degreeID, int companyID, boolean isDelete);
}
