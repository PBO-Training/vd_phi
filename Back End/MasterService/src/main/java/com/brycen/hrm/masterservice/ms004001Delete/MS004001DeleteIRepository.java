package com.brycen.hrm.masterservice.ms004001Delete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.LevelSkillEntity;

/**
 * [Description]: Delete Repository for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS004001DeleteIRepository extends JpaRepository<LevelSkillEntity, Long> {

}
