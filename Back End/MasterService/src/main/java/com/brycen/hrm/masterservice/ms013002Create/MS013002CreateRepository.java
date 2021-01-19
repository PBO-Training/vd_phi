package com.brycen.hrm.masterservice.ms013002Create;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.HolidayEntity;

/**
 * [Description]: Create repository for Holiday Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS013002CreateRepository extends JpaRepository<HolidayEntity, Long> {
    /**
     * [Description]: Find a Holiday with holidayYear<br/>
     * [ Remarks ]:<br/>
     *
     * @param holidayYear
     * @param companyID
     * @return a details holiday Year
     */
    @Query(value = "SELECT * FROM holiday hol WHERE hol.holiday_year = :holidayYear AND hol.company_id = :companyID", nativeQuery = true)
    HolidayEntity findHolidayByHolidayCodeAndCompanyID(@Param("holidayYear") int holidayYear, @Param("companyID") int companyID);
}
