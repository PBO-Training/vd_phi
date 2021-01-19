package com.brycen.hrm.masterservice.ms001002Init;


public class MS001002InitReponse {
	private Object listEmployee;
	private Object listRole;
	
	public MS001002InitReponse(Object listEmployee, Object listRole) {
		super();
		this.listEmployee = listEmployee;
		this.listRole = listRole;
	}
	public Object getListEmployee() {
		return listEmployee;
	}
	public void setListEmployee(Object listEmployee) {
		this.listEmployee = listEmployee;
	}
	public Object getListRole() {
		return listRole;
	}
	public void setListRole(Object listRole) {
		this.listRole = listRole;
	}
	
}
