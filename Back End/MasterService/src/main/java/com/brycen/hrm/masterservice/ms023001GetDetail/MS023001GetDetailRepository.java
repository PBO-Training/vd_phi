package com.brycen.hrm.masterservice.ms023001GetDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.ShiftWorkOptionEntity;
@Repository
public interface MS023001GetDetailRepository extends JpaRepository<ShiftWorkOptionEntity, Long> {
	
	Optional<ShiftWorkOptionEntity> findByShiftWorkOptionIDAndCompanyIDAndIsDelete(long ShiftWorkOptionID, int CompanyID, boolean IsDelete);
}
