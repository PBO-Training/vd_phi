package com.brycen.hrm.masterservice.ms010001Search;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.PositionProjectEntity;

@Repository
public interface MS010001SearchRepository extends JpaRepository<PositionProjectEntity, Long> {

}
