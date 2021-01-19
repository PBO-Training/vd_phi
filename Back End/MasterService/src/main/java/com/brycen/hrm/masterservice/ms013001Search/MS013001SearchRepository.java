package com.brycen.hrm.masterservice.ms013001Search;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.HolidayEntity;

/**
 * [Description]: Interface search repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS013001SearchRepository extends JpaRepository<HolidayEntity, Long>{
    /**
     * [Description]: Find list holiday by holidayID and isDelete<br/>
     * [ Remarks ]:<br/>
     *
     * @param holidayID
     * @param isDelete
     * @return list holiday
     */
    List<HolidayEntity> findAllByCompanyIDAndIsDelete(int companyID, boolean isDelete);
}
