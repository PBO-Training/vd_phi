package com.brycen.hrm.masterservice.ms003001Init;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.generateDropDownList.GenerateDropDownList;
import com.brycen.hrm.entity.SkillTypeEntity;

/**
 * [Description]: Init Service Implementation for Skill Type Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS003001InitImpl implements MS003001InitIService {
	/**
	 * Support find all skill type
	 */
	@Autowired
	private MS003001InitIRepository repo;

	@Override
	public MS003001InitResponse init(int companyID, HttpServletRequest req) {
		MS003001InitResponse response = new MS003001InitResponse();
		List<SkillTypeEntity> listSkillType = repo.findAllByCompanyIDAndIsDelete(companyID, false);
		response.setListSkillType(GenerateDropDownList.generateDefaultValue(
				listSkillType.stream().map(MS003001InitBaseResponse::new).collect(Collectors.toList())));

		return response;
	}

}
