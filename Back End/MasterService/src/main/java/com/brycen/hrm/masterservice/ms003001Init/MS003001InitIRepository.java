package com.brycen.hrm.masterservice.ms003001Init;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.SkillTypeEntity;

/**
 * [Description]: Init Repository for Skill Type Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS003001InitIRepository extends JpaRepository<SkillTypeEntity, Long> {
	List<SkillTypeEntity> findAllByCompanyIDAndIsDelete(int companyID, boolean isDelete);
}
