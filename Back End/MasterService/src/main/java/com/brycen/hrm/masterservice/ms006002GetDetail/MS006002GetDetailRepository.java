package com.brycen.hrm.masterservice.ms006002GetDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
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
public interface MS006002GetDetailRepository extends JpaRepository<LevelLanguageEntity, Long> {
    /**
     * [Description]: Method find level language by companyID and levelLanguageID<br/>
     * [ Remarks ]:<br/>
     *
     * @param levelLanguageID
     * @param companyID
     * @return Language or error with errorName and errorCode
     */
    Optional<LevelLanguageEntity> findByLevelLanguageIDAndCompanyIDAndIsDelete(long levelLanguageID, int companyID, boolean isDelete);
}
