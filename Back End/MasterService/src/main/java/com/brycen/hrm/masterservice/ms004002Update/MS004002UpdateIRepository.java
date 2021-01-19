package com.brycen.hrm.masterservice.ms004002Update;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.LevelSkillEntity;

/**
 * [Description]: Update Repository for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS004002UpdateIRepository extends JpaRepository<LevelSkillEntity, Long> {

    /**
     * [Description]: Find a skill level by levelSkillID, company ID and delete status<br/>
     * [ Remarks ]:<br/>
     *
     * @param levelSkillID
     * @param companyID
     * @param isDelete
     * @return A skill level
     */
    Optional<LevelSkillEntity> findByLevelSkillIDAndCompanyIDAndIsDelete(long levelSkillID, int companyID, boolean isDelete);

    /**
     * [Description]: Find a skill level by levelSkillCode <br/>
     * [ Remarks ]:<br/>
     *
     * @param levelSkillCode
     * @param companyID
     * @param levelSkillID
     * @return A skill level details
     */
    @Query(value = "SELECT * FROM level_skill ls WHERE ls.level_skill_code = :levelSkillCode AND ls.company_id = :companyID AND ls.level_skill_id <> :levelSkillID", nativeQuery = true)
    LevelSkillEntity findLevelSkillByLevelSkillCodeAndCompanyID(@Param("levelSkillCode") String levelSkillCode, @Param("companyID") int companyID, @Param("levelSkillID") Long levelSkillID);
}
