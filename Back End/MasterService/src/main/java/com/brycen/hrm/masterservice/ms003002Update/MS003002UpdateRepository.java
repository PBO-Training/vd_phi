package com.brycen.hrm.masterservice.ms003002Update;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.SkillEntity;

/**
 * [Description]: Interface update repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS003002UpdateRepository extends JpaRepository<SkillEntity, Long> {
    /**
     * [Description]: Method find skill by companyID and skillID<br/>
     * [ Remarks ]:<br/>
     *
     * @param skillID
     * @param companyID
     * @param isDelete
     * @return Skill or error with errorName and errorCode
     */
    Optional<SkillEntity> findBySkillIDAndCompanyIDAndIsDelete(long skillID, int companyID, boolean isDelete);

    /**
     * [Description]: Find a skill by skillCode<br/>
     * [ Remarks ]:<br/>
     *
     * @param skillCode
     * @param companyID
     * @param skillID
     * @return A details skill
     */
    @Query(value = "SELECT * FROM skill s WHERE s.skill_code = :skillCode AND s.company_id = :companyID AND s.skill_id <> :skillID", nativeQuery = true)
    SkillEntity findSkillBySkillCodeAndCompanyID(@Param("skillCode") String skillCode, @Param("companyID") int companyID, @Param("skillID") Long skillID);
}
