package com.brycen.hrm.masterservice.ms005002Create;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.LanguageEntity;

/**
 * [Description]: Interface create repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS005002CreateRepository extends JpaRepository<LanguageEntity, Long> {
    /**
     * [Description]: Find a language by languageCode<br/>
     * [ Remarks ]:<br/>
     *
     * @param languageCode
     * @param companyID
     * @return A language details
     */
    @Query(value = "SELECT * FROM language l WHERE l.language_code = :languageCode AND l.company_id = :companyID", nativeQuery = true)
    LanguageEntity findLanguageByLanguageCodeAndCompanyID(@Param("languageCode") String languageCode, @Param("companyID") int companyID);
}
