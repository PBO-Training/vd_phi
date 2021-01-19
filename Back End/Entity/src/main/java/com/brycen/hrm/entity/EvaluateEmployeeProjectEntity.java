package com.brycen.hrm.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:EvaluateEmployeeProject Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "evaluate_employee_project")
public class EvaluateEmployeeProjectEntity extends BaseEntity {

	/**
	 * Evaluate Employee Project Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "evaluate_employee_project_id")
	private Long evaluateEmployeeProjectID;

	/**
	 * Evaluate Employee Project Name
	 */
	@Column(name = "evaluate_employee_project_name", nullable = false)
	private String evaluateEmployeeProjectName;

	/**
	 * Evaluate Employee Project Code
	 */
	@Column(name = "evaluate_employee_project_code", nullable = false, length = 40)
	private String evaluateEmployeeProjectCode;

	/**
	 * Evaluate Employee Project Description
	 */
	@Column(name = "evaluate_employee_project_description", length = 2000)
	private String evaluateEmployeeProjectDescription;
	/**
	 * List History Work
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "evaluateEmployeeProject")
	private Set<HistoryWorkEntity> listHistoryWork = new HashSet<>();

	/**
	 * List Project
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "evaluateEmployeeProject")
	private Set<EmployeeProjectEntity> listEmployeeProject = new HashSet<>();

	public EvaluateEmployeeProjectEntity() {

	}

	public EvaluateEmployeeProjectEntity(Long evaluateEmployeeProjectID, String evaluateEmployeeProjectName,
			String evaluateEmployeeProjectCode, String evaluateEmployeeProjectDescription,
			Set<EmployeeProjectEntity> listEmployeeProject) {
		super();
		this.evaluateEmployeeProjectID = evaluateEmployeeProjectID;
		this.evaluateEmployeeProjectName = evaluateEmployeeProjectName;
		this.evaluateEmployeeProjectCode = evaluateEmployeeProjectCode;
		this.evaluateEmployeeProjectDescription = evaluateEmployeeProjectDescription;
		this.listEmployeeProject = listEmployeeProject;
	}

	public Long getEvaluateEmployeeProjectID() {
		return evaluateEmployeeProjectID;
	}

	public void setEvaluateEmployeeProjectID(Long evaluateEmployeeProjectID) {
		this.evaluateEmployeeProjectID = evaluateEmployeeProjectID;
	}

	public String getEvaluateEmployeeProjectName() {
		return evaluateEmployeeProjectName;
	}

	public void setEvaluateEmployeeProjectName(String evaluateEmployeeProjectName) {
		this.evaluateEmployeeProjectName = evaluateEmployeeProjectName;
	}

	public String getEvaluateEmployeeProjectCode() {
		return evaluateEmployeeProjectCode;
	}

	public void setEvaluateEmployeeProjectCode(String evaluateEmployeeProjectCode) {
		this.evaluateEmployeeProjectCode = evaluateEmployeeProjectCode;
	}

	public String getEvaluateEmployeeProjectDescription() {
		return evaluateEmployeeProjectDescription;
	}

	public void setEvaluateEmployeeProjectDescription(String evaluateEmployeeProjectDescription) {
		this.evaluateEmployeeProjectDescription = evaluateEmployeeProjectDescription;
	}

	public Set<EmployeeProjectEntity> getListEmployeeProject() {
		return listEmployeeProject;
	}

	public void setListEmployeeProject(Set<EmployeeProjectEntity> listEmployeeProject) {
		this.listEmployeeProject = listEmployeeProject;
	}

}
