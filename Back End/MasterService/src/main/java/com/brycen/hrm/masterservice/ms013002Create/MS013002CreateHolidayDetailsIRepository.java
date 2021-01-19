package com.brycen.hrm.masterservice.ms013002Create;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.HolidayDetailEntity;

/**
 * [Description]: Create Details Repository for Holiday Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS013002CreateHolidayDetailsIRepository extends JpaRepository<HolidayDetailEntity, Long> {

}
