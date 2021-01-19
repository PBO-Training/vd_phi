package com.brycen.hrm.masterservice.ms001001Init;

import com.brycen.hrm.common.base.BaseInitResponse;
import com.brycen.hrm.entity.RoleEntity;

/**
 * [Description]: Initialize Response for Role<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS001001InitRoleResponse extends BaseInitResponse {

	public MS001001InitRoleResponse() {
	}

	public MS001001InitRoleResponse(RoleEntity roleEntity) {
		this.setKeyResponse(roleEntity.getRoleID());
		this.setValueResponse(roleEntity.getRoleName());
	}

	public MS001001InitRoleResponse(Long roleID, String roleName) {
		this.setKeyResponse(roleID);
		this.setValueResponse(roleName);
	}

}