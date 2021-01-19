package com.brycen.hrm.masterservice.ms012001Delete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.VacationTypeEntity;

@Repository
public interface MS012001DeleteIRepository extends JpaRepository<VacationTypeEntity, Long> {

}
