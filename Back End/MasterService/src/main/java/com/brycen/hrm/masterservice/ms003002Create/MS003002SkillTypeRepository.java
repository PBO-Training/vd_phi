package com.brycen.hrm.masterservice.ms003002Create;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.SkillTypeEntity;

@Repository
public interface MS003002SkillTypeRepository extends JpaRepository<SkillTypeEntity, Long> {
	/**
	 * [Description]: Find a skill type with skillTypeID<br/>
	 * [ Remarks ]:<br/>
	 *
	 * @param skillTypeID
	 * @param companyID
	 * @param isDelete
	 * @return skill type
	 */
	SkillTypeEntity findBySkillTypeIDAndCompanyIDAndIsDelete(long skillTypeID, int companyID, boolean isDelete);

}
