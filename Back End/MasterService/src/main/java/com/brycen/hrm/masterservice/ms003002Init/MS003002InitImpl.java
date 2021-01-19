package com.brycen.hrm.masterservice.ms003002Init;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
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
public class MS003002InitImpl implements MS003002InitIService {
	/**
	 * Support find all skill type
	 */
	@Autowired
	private MS003002InitIRepository repo;

	@Override
	public BaseResponse init(int companyID, HttpServletRequest req) {
		BaseResponse response = new BaseResponse();
		List<SkillTypeEntity> listSkillType = repo.findAllByCompanyIDAndIsDelete(companyID, false);
		List<Object> result = listSkillType.stream().map(MS003002InitResponse::new).collect(Collectors.toList());
		response.setContent(result);
		return response;
	}

}
