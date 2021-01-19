package com.brycen.hrm.masterservice.ms003002GetDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
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
public interface MS003002GetDetailRepository extends JpaRepository<SkillEntity, Long> {
    /**
     * [Description]: Method find skill by companyID and skillID<br/>
     * [ Remarks ]:<br/>
     *
     * @param skillID
     * @param companyID
     * @return Skill or error with errorName and errorCode
     */
    Optional<SkillEntity> findBySkillIDAndCompanyIDAndIsDelete(long skillID, int companyID, boolean isDelete);
}
