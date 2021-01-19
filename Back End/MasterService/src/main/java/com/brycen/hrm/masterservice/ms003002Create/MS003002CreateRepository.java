package com.brycen.hrm.masterservice.ms003002Create;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.SkillEntity;

/**
 * [Description]: Interface create repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS003002CreateRepository extends JpaRepository<SkillEntity, Long> {

    /**
     * [Description]: Find a skill with skillCode<br/>
     * [ Remarks ]:<br/>
     *
     * @param skillCode
     * @param companyID
     * @return A skill details
     */
    @Query(value = "SELECT * FROM skill s WHERE s.skill_code = :skillCode AND s.company_id = :companyID", nativeQuery = true)
    SkillEntity findSkillBySkillCodeAndCompanyID(@Param("skillCode") String skillCode, @Param("companyID") int companyID);

}
