package com.brycen.hrm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;
import com.brycen.hrm.entity.primaryKey.PrimaryKeyEmpProject;

/**
 * [Description]:EmployeeProject Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "employee_project")
@IdClass(PrimaryKeyEmpProject.class)
public class EmployeeProjectEntity extends BaseEntity{

    /**
     * Id of Employee
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    /**
     * Id of Project
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    /**
     * Position Project Id
     */
    @ManyToOne
    @JoinColumn(name = "position_project_id")
    private PositionProjectEntity postionProject;

    /**
     * Evaluate Employee Project Id
     */
    @ManyToOne
    @JoinColumn(name = "evaluate_employee_project_id")
    private EvaluateEmployeeProjectEntity evaluateEmployeeProject;

    /**
     * Date Join Project
     */
    @Column(name = "date_join_project")
    private Date dateJoinProject;

    /**
     * Date Out Project
     */
    @Column(name = "date_out_project")
    private Date dateOutProject;

    /**
     * Description for employee in project
     */
    @Column(name = "description")
    private String description;

    public EmployeeProjectEntity() {

    }

    public EmployeeProjectEntity(EmployeeEntity employee, ProjectEntity project, PositionProjectEntity postionProject,
            EvaluateEmployeeProjectEntity evaluateEmployeeProject, Date dateJoinProject, Date dateOutProject, String description) {
        super();
        this.employee = employee;
        this.project = project;
        this.postionProject = postionProject;
        this.evaluateEmployeeProject = evaluateEmployeeProject;
        this.dateJoinProject = dateJoinProject;
        this.dateOutProject = dateOutProject;
        this.description = description;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public PositionProjectEntity getPostionProject() {
        return postionProject;
    }

    public void setPostionProject(PositionProjectEntity postionProject) {
        this.postionProject = postionProject;
    }

    public EvaluateEmployeeProjectEntity getEvaluateEmployeeProject() {
        return evaluateEmployeeProject;
    }

    public void setEvaluateEmployeeProject(EvaluateEmployeeProjectEntity evaluateEmployeeProject) {
        this.evaluateEmployeeProject = evaluateEmployeeProject;
    }

    public Date getDateJoinProject() {
        return dateJoinProject;
    }

    public void setDateJoinProject(Date dateJoinProject) {
        this.dateJoinProject = dateJoinProject;
    }

    public Date getDateOutProject() {
        return dateOutProject;
    }

    public void setDateOutProject(Date dateOutProject) {
        this.dateOutProject = dateOutProject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String notes) {
        this.description = notes;
    }

}
