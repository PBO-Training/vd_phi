package com.brycen.hrm.masterservice.ms006002Update;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.LevelLanguageEntity;

/**
 * [Description]: Interface update repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS006002UpdateRepository extends JpaRepository<LevelLanguageEntity, Long>{
    /**
     * [Description]: Method find level language by companyID and levelLanguageID<br/>
     * [ Remarks ]:<br/>
     *
     * @param levelLanguageID
     * @param companyID
     * @param isDelete
     * @return Language or error with errorName and errorCode
     */
    Optional<LevelLanguageEntity> findByLevelLanguageIDAndCompanyIDAndIsDelete(long LevelLanguageID, int companyID, boolean isDelete);

    /**
     * [Description]: Find a level language details<br/>
     * [ Remarks ]:<br/>
     *
     * @param levelLanguageCode
     * @param companyID
     * @param levelLanguageID
     * @return A level language details
     */
    @Query(value = "SELECT * FROM level_language ll WHERE ll.level_language_code = :levelLanguageCode AND ll.company_id = :companyID AND ll.level_language_id <> :levelLanguageID", nativeQuery = true)
    LevelLanguageEntity findLevelLanguageByLevelLanguageCodeAndCompanyID(@Param("levelLanguageCode") String levelLanguageCode, @Param("companyID") int companyID, @Param("levelLanguageID") Long levelLanguageID);
}
