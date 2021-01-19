package com.brycen.hrm.masterservice.ms004002Create;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.LevelSkillEntity;

/**
 * [Description]: Create Repository for LevelSkill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS004002CreateIRepository extends JpaRepository<LevelSkillEntity, Long> {
    /**
     * [Description]: Find a skill level by levelSkillCode<br/>
     * [ Remarks ]:<br/>
     *
     * @param levelSkillCode
     * @param companyID
     * @return A skill level details
     */
    @Query(value = "SELECT * FROM level_skill ls WHERE ls.level_skill_code = :levelSkillCode AND ls.company_id = :companyID", nativeQuery = true)
    LevelSkillEntity findLevelSkillByLevelSkillCodeAndCompanyID(@Param("levelSkillCode") String levelSkillCode, @Param("companyID") int companyID);
}
