package com.brycen.hrm.masterservice.ms001002GetDetail;

import com.brycen.hrm.entity.EmployeeEntity;

/**
 * [Description]: Employee Response for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS001002GetDetailEmployeeResponse {

	/**
	 * EmployeeID
	 */
	private Long employeeID;

	/**
	 * EmployeeCode
	 */
	private String employeeCode;

	/**
	 * Employee first name
	 */
	private String firstName;

	/**
	 * Employee last name
	 */
	private String lastName;

	public MS001002GetDetailEmployeeResponse() {
		super();
	}

	public MS001002GetDetailEmployeeResponse(EmployeeEntity employeeEntity) {
		super();
		this.employeeID = employeeEntity.getEmployeeID();
		this.employeeCode = employeeEntity.getEmployeeCode();
		this.firstName = employeeEntity.getFirstName();
		this.lastName = employeeEntity.getLastName();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Long employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

}
