package com.brycen.hrm.masterservice.ms001002Init;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.EmployeeEntity;
import com.brycen.hrm.entity.RoleEntity;

/**
 * [Description]: Initialize Service Implementation for details screen of User
 * Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS001002InitImpl implements MS001002InitIService {

	/**
	 * Support find all employee
	 */
	@Autowired
	private MS001002InitEmployeeRepository employeeRepo;

	/**
	 * Support find all role
	 */
	@Autowired
	private MS001002InitRoleRepository roleRepo;

	@Override
	public BaseResponse init(int companyID) {
		BaseResponse baseRes = new BaseResponse();
		List<EmployeeEntity> listEmployee = employeeRepo.findAllByCompanyIDAndIsDelete(companyID, false);
		List<RoleEntity> listRole = roleRepo.findAllByCompanyIDAndIsDelete(companyID, false);
		List<Object> result1 = listEmployee.stream().map(MS001002InitEmployeeResponse::new).collect(Collectors.toList());
		List<Object> result2 = listRole.stream().map(MS001002InitRoleResponse::new).collect(Collectors.toList());
		MS001002InitReponse result = new MS001002InitReponse(result1, result2);
		baseRes.setContent(result);
		return baseRes;
	}

}
