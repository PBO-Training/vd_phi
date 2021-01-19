package com.brycen.hrm.masterservice.ms001002Init;

import com.brycen.hrm.entity.RoleEntity;

public class MS001002InitRoleResponse {
	/**
	 * role id
	 */
	private long roleID;
	/**
	 * role name
	 */
	private String roleName;

	public MS001002InitRoleResponse() {
		super();
	}

	public MS001002InitRoleResponse(RoleEntity roleEntity) {
		super();
		this.roleID = roleEntity.getRoleID();
		this.roleName = roleEntity.getRoleName();
	}

	public long getRoleID() {
		return roleID;
	}

	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
