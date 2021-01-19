package com.brycen.hrm.masterservice.ms005002GetDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
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
public interface MS005002GetDetailRepository extends JpaRepository<LanguageEntity, Long> {
    /**
     * [Description]: Method find language by companyID and languageID<br/>
     * [ Remarks ]:<br/>
     *
     * @param languageID
     * @param companyID
     * @return Language or error with errorName and errorCode
     */
    Optional<LanguageEntity> findByLanguageIDAndCompanyIDAndIsDelete(long languageID, int companyID, boolean isDelete);
}
