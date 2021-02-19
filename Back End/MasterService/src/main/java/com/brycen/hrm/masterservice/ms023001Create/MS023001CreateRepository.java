package com.brycen.hrm.masterservice.ms023001Create;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.ShiftWorkOptionEntity;

/**
 * [Description]: Delete Repository for Scope Work Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS023001CreateRepository extends JpaRepository<ShiftWorkOptionEntity, Long>{
	
	@Query(value = "SELECT * FROM shift_work_option s WHERE s.shift_work_option_code = :shiftWorkOptionCode AND s.company_id = :companyID", nativeQuery = true)
    ShiftWorkOptionEntity findByShiftWorkOptionCodeAndCompanyID(@Param("shiftWorkOptionCode") String shiftWorkOptionCode, @Param("companyID") int companyID);
}

