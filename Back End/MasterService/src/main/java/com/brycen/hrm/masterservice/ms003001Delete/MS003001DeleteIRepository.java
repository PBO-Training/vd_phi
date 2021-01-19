package com.brycen.hrm.masterservice.ms003001Delete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.SkillEntity;

@Repository
public interface MS003001DeleteIRepository extends JpaRepository<SkillEntity, Long> {

}
