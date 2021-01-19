package com.brycen.hrm.masterservice.ms004002GetDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.LevelSkillEntity;

/**
 * [Description]: Get Details Repository for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS004002GetDetailIRepository extends JpaRepository<LevelSkillEntity, Long> {

    /**
     * [Description]: Find a skill level with levelSkillID, companyID and delete status<br/>
     * [ Remarks ]:<br/>
     *
     * @param levelSkillID
     * @param companyID
     * @param isDelete
     * @return A skill level
     */
    Optional<LevelSkillEntity> findByLevelSkillIDAndCompanyIDAndIsDelete(long levelSkillID, int companyID, boolean isDelete);

}
