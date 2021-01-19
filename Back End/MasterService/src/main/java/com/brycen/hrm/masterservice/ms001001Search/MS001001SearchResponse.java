package com.brycen.hrm.masterservice.ms001001Search;

import com.brycen.hrm.entity.UserEntity;

/**
 * [Description]: Search Response for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS001001SearchResponse {

	/**
	 * User ID
	 */
	private Long userID;

	/**
	 * Username
	 */
	private String username;

	/**
	 * Username
	 */
	private String employeeName;

	/**
	 * Role to correspods to user
	 */
	private MS001001RoleResponse role;

	public MS001001SearchResponse() {
		super();
	}

	public MS001001SearchResponse(UserEntity userEntity) {
		super();
		this.userID = userEntity.getUserID();
		this.username = userEntity.getUsername();
		this.role = new MS001001RoleResponse(userEntity.getRole());
		this.employeeName = userEntity.getEmployee().getFirstName() + " " + userEntity.getEmployee().getLastName();
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public MS001001RoleResponse getRole() {
		return role;
	}

	public void setRole(MS001001RoleResponse role) {
		this.role = role;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

}
