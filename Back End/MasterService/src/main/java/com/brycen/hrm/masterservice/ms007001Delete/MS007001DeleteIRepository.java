package com.brycen.hrm.masterservice.ms007001Delete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.PositionEmployeeEntity;

@Repository
public interface MS007001DeleteIRepository extends JpaRepository<PositionEmployeeEntity, Long> {

}
