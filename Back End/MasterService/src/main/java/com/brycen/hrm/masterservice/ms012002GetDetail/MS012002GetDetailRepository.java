package com.brycen.hrm.masterservice.ms012002GetDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.VacationTypeEntity;

/**
 * [Description]: Interface update repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS012002GetDetailRepository extends JpaRepository<VacationTypeEntity, Long> {
    /**
     * [Description]: Method find vacation type by companyID and vacationTypeID<br/>
     * [ Remarks ]:<br/>
     *
     * @param vacationTypeID
     * @param companyID
     * @return vacation type or error with errorName and errorCode
     */
    Optional<VacationTypeEntity> findByvacationTypeIDAndCompanyIDAndIsDelete(long vacationTypeID, int companyID, boolean isDelete);
}
