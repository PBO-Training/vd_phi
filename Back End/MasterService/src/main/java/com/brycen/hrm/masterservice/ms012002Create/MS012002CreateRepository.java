package com.brycen.hrm.masterservice.ms012002Create;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.VacationTypeEntity;

/**
 * [Description]: Interface create repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS012002CreateRepository extends JpaRepository<VacationTypeEntity, Long> {
    /**
     * [Description]: Find a vacation type with vacationTypeCode<br/>
     * [ Remarks ]:<br/>
     *
     * @param vacationTypeCode
     * @param companyID
     * @return a details vacation type
     */
    @Query(value = "SELECT * FROM vacation_type v WHERE v.vacation_type_code = :vacationTypeCode AND v.company_id = :companyID", nativeQuery = true)
    VacationTypeEntity findVacationTypeByVacationTypeCodeAndCompanyID(@Param("vacationTypeCode") String vacationTypeCode, @Param("companyID") int companyID);

}
