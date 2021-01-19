package com.brycen.hrm.masterservice.ms013001Delete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.HolidayEntity;

@Repository
public interface MS013001DeleteRepository extends JpaRepository<HolidayEntity, Long>{
    
}
