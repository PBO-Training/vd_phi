package com.brycen.hrm.masterservice.ms006002Create;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.LevelLanguageEntity;

/**
 * [Description]: Interface create repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS006002CreateRepository extends JpaRepository<LevelLanguageEntity, Long>{

    /**
     * [Description]: Find a language level details by levelLanguageCode<br/>
     * [ Remarks ]:<br/>
     *
     * @param levelLanguageCode
     * @param companyID
     * @return A language level details
     */
    @Query(value = "SELECT * FROM level_language ll WHERE ll.level_language_code = :levelLanguageCode AND ll.company_id = :companyID", nativeQuery = true)
    LevelLanguageEntity findLevelLanguageByLevelLanguageCodeAndCompanyID(@Param("levelLanguageCode") String levelLanguageCode, @Param("companyID") int companyID);
}
