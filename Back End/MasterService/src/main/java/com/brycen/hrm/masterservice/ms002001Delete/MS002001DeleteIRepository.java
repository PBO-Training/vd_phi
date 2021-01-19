package com.brycen.hrm.masterservice.ms002001Delete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.DepartmentEntity;

@Repository
public interface MS002001DeleteIRepository extends JpaRepository<DepartmentEntity, Long> {

}
