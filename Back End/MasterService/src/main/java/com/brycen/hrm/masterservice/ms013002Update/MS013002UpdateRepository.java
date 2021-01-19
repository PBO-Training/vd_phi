package com.brycen.hrm.masterservice.ms013002Update;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.HolidayEntity;

/**
 * [Description]: Interface update repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS013002UpdateRepository extends JpaRepository<HolidayEntity, Long> {
    /**
     * [Description]: Method find holiday by companyID and holidayID<br/>
     * [ Remarks ]:<br/>
     *
     * @param holidayID
     * @param companyID
     * @param isDelete
     * @return Holiday or error with errorName and errorCode
     */
    Optional<HolidayEntity> findByHolidayIDAndCompanyIDAndIsDelete(long holidayID, int companyID, boolean isDelete);

    /**
     * [Description]: Find a holiday details<br/>
     * [ Remarks ]:<br/>
     *
     * @param holidayName
     * @param companyID
     * @param holidayID
     * @return a holiday details
     */
    @Query(value = "SELECT * FROM holiday hol WHERE hol.holiday_year = :holidayYear AND hol.company_id = :companyID AND hol.holiday_id <> :holidayID", nativeQuery = true)
    HolidayEntity findHolidayByHolidayCodeAndCompanyID(@Param("holidayYear") int holidayYear, @Param("companyID") int companyID,
            @Param("holidayID") Long holidayID);
}
