package com.brycen.hrm.masterservice.ms005002Update;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.LanguageEntity;

/**
 * [Description]: Interface update repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS005002UpdateRepository extends JpaRepository<LanguageEntity, Long> {
    /**
     * [Description]: Method find language by companyID and languageID<br/>
     * [ Remarks ]:<br/>
     *
     * @param languageID
     * @param companyID
     * @param isDelete
     * @return Language or error with errorName and errorCode
     */
    Optional<LanguageEntity> findByLanguageIDAndCompanyIDAndIsDelete(long languageID, int companyID, boolean isDelete);

    /**
     * [Description]: Find a language details by languageCode<br/>
     * [ Remarks ]:<br/>
     *
     * @param languageCode
     * @param companyID
     * @param languageID
     * @return A language details
     */
    @Query(value = "SELECT * FROM language l WHERE l.language_code = :languageCode AND l.company_id = :companyID AND l.language_id <> :languageID", nativeQuery = true)
    LanguageEntity findLanguageByLanguageCodeAndCompanyID(@Param("languageCode") String languageCode, @Param("companyID") int companyID, @Param("languageID") Long languageID);
}
