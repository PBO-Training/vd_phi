package com.brycen.hrm.masterservice.ms013002Update;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.brycen.hrm.entity.HolidayDetailEntity;

/**
 * [Description]: Interface update repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS013002UpdateDetailHolidayRepository extends JpaRepository<HolidayDetailEntity, Long> {
    /**
     * [Description]: Method find holiday by companyID and holidayID<br/>
     * [ Remarks ]:<br/>
     *
     * @param holidayID
     * @param companyID
     * @param isDelete
     * @return Holiday or error with errorName and errorCode
     */
    @Query(value = "SELECT * FROM holiday_detail hol WHERE hol.holiday_id =:holidayID AND hol.company_id =:companyID AND is_delete = 0", nativeQuery = true)
    List<HolidayDetailEntity> findHolidayDetailByHolidayIDAndCompanyID(@Param("holidayID") Long employeeID, @Param("companyID") int companyID);

    /**
     * [Description]: Method find Holiday by companyID and holidayDetailID<br/>
     * [ Remarks ]:<br/>
     *
     * @param holidayDetailID
     * @param companyID
     * @return Optional of HolidayDetailEntity
     */
    Optional<HolidayDetailEntity> findByholidayDetailIDAndCompanyIDAndIsDelete(long holidayDetailID, int companyID, boolean isDelete);

    // Optional<HolidayDetailEntity> findByholidayIDAndCompanyIDAndIsDelete(long holidayID, int companyID, boolean isDelete);
    @Modifying
    @Query(value = "INSERT INTO holiday_detail (company_id, holiday_detail_name, holiday_detail_date, holiday_id, is_delete) VALUES (:companyID, :holidayDetailName, :holidayDetailDate, :holidayID, :isDelete)", nativeQuery = true)
    @Transactional
    void saveDetailHoliday(@Param("companyID") int companyID, @Param("holidayDetailName") String holidayDetailName,
            @Param("holidayDetailDate") Date holidayDetailDate, @Param("holidayID") Long HolidayID, @Param("isDelete") boolean isDelete);

//    void DeleteByHolidayIDAndCompanyID(Long HolidayID, int companyID);
}
