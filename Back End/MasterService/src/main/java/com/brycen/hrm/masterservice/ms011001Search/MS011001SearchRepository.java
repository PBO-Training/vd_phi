package com.brycen.hrm.masterservice.ms011001Search;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.LevelProjectEntity;

@Repository
public interface MS011001SearchRepository extends JpaRepository<LevelProjectEntity, Long> {

}
