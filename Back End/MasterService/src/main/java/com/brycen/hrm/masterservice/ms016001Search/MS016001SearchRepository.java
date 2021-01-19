package com.brycen.hrm.masterservice.ms016001Search;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.EvaluateProjectEntity;

@Repository
public interface MS016001SearchRepository extends JpaRepository<EvaluateProjectEntity, Long> {

}
