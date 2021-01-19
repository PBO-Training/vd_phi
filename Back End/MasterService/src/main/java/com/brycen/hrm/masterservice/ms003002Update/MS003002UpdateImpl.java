package com.brycen.hrm.masterservice.ms003002Update;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.SqlValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.SkillEntity;
import com.brycen.hrm.entity.SkillTypeEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: This is place to update skill<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS003002UpdateImpl implements MS003002UpdateService {
	@Autowired
	private MS003002UpdateRepository updateRepository;
	@Autowired
	private MS003002UpdateSkillTypeRepository skillTypeRepository;

	@Autowired
	LoggerService logger;

	public ErrorResponse checkValue(MS003002UpdateRequest request, Optional<SkillEntity> currentSkill, int companyID) {
		ErrorResponse error = new ErrorResponse();
		StringBuffer errorItemName = new StringBuffer();
		StringBuffer errorItemCode = new StringBuffer();

		// Check duplicate code
		SkillEntity skill = updateRepository.findSkillBySkillCodeAndCompanyID(request.getSkillCode().trim(), companyID,
				currentSkill.get().getSkillID());
		if (skill != null) {
			errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER)
					.append(ErrorValue.PACKAGE_SKILL).append(ErrorValue.API_UPDATE_DETAIL_SKILL)
					.append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
			errorItemName.append("skillCode");
			error.setCode(errorItemCode.toString());
			error.setItemName(errorItemName.toString());
			return error;
		} else {
			// check null code
			if (CheckValueService.checkNull(request.getSkillCode().trim())) {
				errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER)
						.append(ErrorValue.PACKAGE_SKILL).append(ErrorValue.API_UPDATE_DETAIL_SKILL)
						.append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
				errorItemName.append("skillCode");
				error.setCode(errorItemCode.toString());
				error.setItemName(errorItemName.toString());
				return error;
			}
		}

		// Check skill name is null
		if (CheckValueService.checkNull(request.getSkillName().trim())) {
			errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER)
					.append(ErrorValue.PACKAGE_SKILL).append(ErrorValue.API_UPDATE_DETAIL_SKILL)
					.append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
			errorItemName.append("skillName");
			error.setCode(errorItemCode.toString());
			error.setItemName(errorItemName.toString());
			return error;
		}
		// Check skill type id is null
		SkillTypeEntity skillType = skillTypeRepository
				.findBySkillTypeIDAndCompanyIDAndIsDelete(request.getSkillTypeID(), companyID, false);
		if (skillType == null) {
			errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER)
					.append(ErrorValue.PACKAGE_SKILL).append(ErrorValue.API_UPDATE_DETAIL_SKILL)
					.append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
			errorItemName.append("skillTypeID");
			error.setCode(errorItemCode.toString());
			error.setItemName(errorItemName.toString());
			return error;
		}

		if (CheckValueService.checkMaxLength(request.getSkillCode().trim(), SqlValue.LENGTH_CODE)
				|| CheckValueService.checkMaxLength(request.getSkillDescription().trim(), SqlValue.LENGTH_DESCRIPTION)
				|| CheckValueService.checkMaxLength(request.getSkillName().trim(), SqlValue.LENGTH_STRING)) {

			errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER)
					.append(ErrorValue.PACKAGE_SKILL).append(ErrorValue.API_UPDATE_DETAIL_SKILL)
					.append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);

		}
		if (CheckValueService.checkMaxLength(request.getSkillCode().trim(), SqlValue.LENGTH_CODE)) {
			errorItemName.append("skillCode");
			error.setCode(errorItemCode.toString());
			error.setItemName(errorItemName.toString());
			return error;
		}
		if (CheckValueService.checkMaxLength(request.getSkillDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
			errorItemName.append("skillDescription");
			error.setCode(errorItemCode.toString());
			error.setItemName(errorItemName.toString());
			return error;
		}
		if (CheckValueService.checkMaxLength(request.getSkillName().trim(), SqlValue.LENGTH_STRING)) {
			errorItemName.append("skillName");
			error.setCode(errorItemCode.toString());
			error.setItemName(errorItemName.toString());
			return error;
		}
		if (!currentSkill.isPresent()) {
			errorItemName.append("skillID");
			errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER)
					.append(ErrorValue.PACKAGE_SKILL).append(ErrorValue.API_UPDATE_DETAIL_SKILL)
					.append(ErrorValue.METHOD_PUT).append(ErrorValue.REASON_UNKNOWN_VALUE);
			error.setCode(errorItemCode.toString());
			error.setItemName(errorItemName.toString());
			return error;
		}
		// check 'space' and special symbol for field code
		if (CheckValueService.isAlphaNumber(request.getSkillCode().trim())) {
			errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER)
					.append(ErrorValue.PACKAGE_SKILL).append(ErrorValue.API_UPDATE_DETAIL_SKILL)
					.append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
			errorItemName.append("skillCode");
			error.setCode(errorItemCode.toString());
			error.setItemName(errorItemName.toString());
			return error;
		}
		return null;
	}

	@Override
	public BaseResponse save(MS003002UpdateRequest request, int companyID) {
		BaseResponse baseRes = new BaseResponse();
		Optional<SkillEntity> currentSkill = updateRepository.findBySkillIDAndCompanyIDAndIsDelete(request.getSkillID(),
				companyID, false);
		ErrorResponse error = checkValue(request, currentSkill, companyID);
		if (error != null) {
			baseRes.setError(error);
			logger.write(LogLevel.ERROR, UrlAPI.MS003002_UPDATE_SKILL, request, baseRes, "");
			return baseRes;
		}
		String skillCode = request.getSkillCode().trim().toUpperCase();
		SkillEntity s = currentSkill.get();
		s.setSkillName(request.getSkillName().trim());
		s.setIsDelete(s.getIsDelete());
		s.setSkillCode(skillCode);
		s.setSkillDescription(request.getSkillDescription().trim());
		SkillTypeEntity skillTypeEntity = skillTypeRepository
				.findBySkillTypeIDAndCompanyIDAndIsDelete(request.getSkillTypeID(), companyID, false);
		s.setSkillType(skillTypeEntity);
		updateRepository.save(s);
		return baseRes;
	}
}
