package com.brycen.hrm.masterservice.ms006001Search;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.LevelLanguageEntity;

@Repository
public interface MS006001SearchRepository extends JpaRepository<LevelLanguageEntity, Long> {
}
