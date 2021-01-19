package com.brycen.hrm.masterservice.ms005001Delete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.LanguageEntity;

@Repository
public interface MS005001DeleteRepository extends JpaRepository<LanguageEntity, Long>{

}
