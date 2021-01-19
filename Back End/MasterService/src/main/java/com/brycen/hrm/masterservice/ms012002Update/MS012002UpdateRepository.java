package com.brycen.hrm.masterservice.ms012002Update;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
public interface MS012002UpdateRepository extends JpaRepository<VacationTypeEntity, Long> {
    /**
     * [Description]: Method find vacation type by companyID and vacationTypeID<br/>
     * [ Remarks ]:<br/>
     *
     * @param vacationTypeID
     * @param companyID
     * @param isDelete
     * @return vacation type or error with errorName and errorCode
     */
    Optional<VacationTypeEntity> findByVacationTypeIDAndCompanyIDAndIsDelete(long vacationTypeID, int companyID, boolean isDelete);

    /**
     * [Description]: Find a vacation type details<br/>
     * [ Remarks ]:<br/>
     *
     * @param vacationTypeCode
     * @param companyID
     * @param vacationTypeID
     * @return a vacation type details
     */
    @Query(value = "SELECT * FROM vacation_type v WHERE v.vacation_type_code = :vacationTypeCode AND v.company_id = :companyID AND v.vacation_type_id <> :vacationTypeID", nativeQuery = true)
    VacationTypeEntity findVacationTypeByVacationTypeCodeAndCompanyID(@Param("vacationTypeCode") String vacationTypeCode, @Param("companyID") int companyID,
            @Param("vacationTypeID") Long vacationTypeID);

}
