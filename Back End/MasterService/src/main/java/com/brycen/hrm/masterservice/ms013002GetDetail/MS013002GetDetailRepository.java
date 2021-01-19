package com.brycen.hrm.masterservice.ms013002GetDetail;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
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
public interface MS013002GetDetailRepository extends JpaRepository<HolidayEntity, Long> {
    /**
     * [Description]: Method find holiday by companyID and holidayID<br/>
     * [ Remarks ]:<br/>
     *
     * @param holidayID
     * @param companyID
     * @return Department or error with errorName and errorCode
     */
    List<HolidayEntity> findHolidayByHolidayIDAndCompanyIDAndIsDelete(long holidayID, int companyID, boolean isDelete);

    /**
     * [Description]: Method find one holiday by companyID and holidayID<br/>
     * [ Remarks ]:<br/>
     *
     * @param holidayID
     * @param companyID
     * @return Department or error with errorName and errorCode
     */
    Optional<HolidayEntity> findOneHolidayByHolidayIDAndCompanyIDAndIsDelete(long holidayID, int companyID, boolean isDelete);
}
