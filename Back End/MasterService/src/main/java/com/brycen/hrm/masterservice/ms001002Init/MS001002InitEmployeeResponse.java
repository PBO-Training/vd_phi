package com.brycen.hrm.masterservice.ms001002Init;

import com.brycen.hrm.entity.EmployeeEntity;

/**
 * [Description]: Initialize Response for details screen of User Master
 * Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS001002InitEmployeeResponse {

	/**
	 * employee id
	 */
	private long employeeID;

	/**
	 * employee code
	 */
	private String employeeCode;

	/**
	 * employee email
	 */
	private String email;

	/**
	 * employee first name
	 */
	private String firstName;

	/**
	 * employee last name
	 */
	private String lastName;

	public MS001002InitEmployeeResponse() {
		super();
	}

	public MS001002InitEmployeeResponse(EmployeeEntity employeeEntity) {
		super();
		this.employeeID = employeeEntity.getEmployeeID();
		this.employeeCode = employeeEntity.getEmployeeCode();
		this.email = employeeEntity.getEmail();
		this.firstName = employeeEntity.getFirstName();
		this.lastName = employeeEntity.getLastName();
	}

	public long getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

}
