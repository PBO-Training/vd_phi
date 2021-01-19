package com.brycen.hrm.masterservice.ms003002Create;

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
 * [Description]: This is place make to create new skill<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS003002CreateImpl implements MS003002CreateService {
	@Autowired
	private MS003002CreateRepository createRepository;

	@Autowired
	private MS003002SkillTypeRepository skillTypeRepository;

	@Autowired
	LoggerService logger;

	public ErrorResponse checkValue(MS003002CreateRequest request, int companyID) {
		ErrorResponse error = new ErrorResponse();
		StringBuffer errorItemName = new StringBuffer();
		StringBuffer errorItemCode = new StringBuffer();

		// Check skill code is null
		if (CheckValueService.checkNull(request.getSkillCode().trim())) {
			errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER)
					.append(ErrorValue.PACKAGE_SKILL).append(ErrorValue.API_CREATE_DETAIL_SKILL)
					.append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_UNSETTING);
			errorItemName.append("skillCode");
			error.setCode(errorItemCode.toString());
			error.setItemName(errorItemName.toString());
			return error;
		}

		// Check skill code is duplicate
		SkillEntity skill = createRepository.findSkillBySkillCodeAndCompanyID(request.getSkillCode().trim(), companyID);
		if (skill != null) {
			errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER)
					.append(ErrorValue.PACKAGE_SKILL).append(ErrorValue.API_CREATE_DETAIL_SKILL)
					.append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
			errorItemName.append("skillCode");
			error.setCode(errorItemCode.toString());
			error.setItemName(errorItemName.toString());
			return error;
		}

		// Check skill name is null
		if (CheckValueService.checkNull(request.getSkillName().trim())) {
			errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER)
					.append(ErrorValue.PACKAGE_SKILL).append(ErrorValue.API_CREATE_DETAIL_SKILL)
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
					.append(ErrorValue.PACKAGE_SKILL).append(ErrorValue.API_CREATE_DETAIL_SKILL)
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
					.append(ErrorValue.PACKAGE_SKILL).append(ErrorValue.API_CREATE_DETAIL_SKILL)
					.append(ErrorValue.METHOD_POST);

		}
		if (CheckValueService.checkMaxLength(request.getSkillCode().trim(), SqlValue.LENGTH_CODE)) {
			errorItemName.append("skillCode");
			errorItemCode.append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
			error.setCode(errorItemCode.toString());
			error.setItemName(errorItemName.toString());
			return error;
		}
		if (CheckValueService.checkMaxLength(request.getSkillDescription().trim(), SqlValue.LENGTH_DESCRIPTION)) {
			errorItemName.append("skillDescription");
			errorItemCode.append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
			error.setCode(errorItemCode.toString());
			error.setItemName(errorItemName.toString());
			return error;
		}
		if (CheckValueService.checkMaxLength(request.getSkillName().trim(), SqlValue.LENGTH_STRING)) {
			errorItemName.append("skillName");
			errorItemCode.append(ErrorValue.REASON_CHARACTER_LENGHT_ILLEGAL);
			error.setCode(errorItemCode.toString());
			error.setItemName(errorItemName.toString());
			return error;
		}

		// check 'space' and special symbol for field code
		if (CheckValueService.isAlphaNumber(request.getSkillCode().trim())) {
			errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER)
					.append(ErrorValue.PACKAGE_SKILL).append(ErrorValue.API_CREATE_DETAIL_SKILL)
					.append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_CHARACTER_ILLEGAL_OR_CANNOT_USED);
			errorItemName.append("skillCode");
			error.setCode(errorItemCode.toString());
			error.setItemName(errorItemName.toString());
			return error;
		}
		return null;
	}

	@Override
	public BaseResponse save(MS003002CreateRequest request, int companyID) {
		BaseResponse baseRes = new BaseResponse();
		ErrorResponse error = checkValue(request, companyID);
		if (error != null) {
			baseRes.setError(error);
			logger.write(LogLevel.ERROR, UrlAPI.MS003002_CREATE_SKILL, request, baseRes, "");
			return baseRes;
		} else {
			SkillEntity skillEntity = new SkillEntity();
			skillEntity.setCompanyID(companyID);
			skillEntity.setSkillCode(request.getSkillCode().trim().toUpperCase());
			skillEntity.setSkillName(request.getSkillName());
			skillEntity.setSkillDescription(request.getSkillDescription());
			SkillTypeEntity skillTypeEntity = skillTypeRepository
					.findBySkillTypeIDAndCompanyIDAndIsDelete(request.getSkillTypeID(), companyID, false);
			skillEntity.setSkillType(skillTypeEntity);
			createRepository.save(skillEntity);
			return baseRes;
		}

	}
}
