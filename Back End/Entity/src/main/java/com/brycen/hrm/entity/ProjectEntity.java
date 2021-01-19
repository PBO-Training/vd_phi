package com.brycen.hrm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:Project Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "project")
public class ProjectEntity extends BaseEntity {
    /**
     * Project Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectID;

    /**
     * Project Name
     */
    @Column(name = "project_name", nullable = false)
    private String projectName;

    /**
     * Project Code
     */
    @Column(name = "project_code", length = 40, nullable = false)
    private String projectCode;

    /**
     * Project Description
     */
    @Column(name = "project_description", length = 2000)
    private String projectDescription;

    /**
     * Project Start Date
     */
    @Column(name = "project_start_date")
    private Date projectStartDate;

    /**
     * Project End Date
     */
    @Column(name = "project_end_date")
    private Date projectEndDate;

    /**
     * Project Cost
     */
    @Column(name = "project_effort")
    private int projectEffort;

    /**
     * Level Project
     */
    @ManyToOne
    @JoinColumn(name = "level_project_id")
    private LevelProjectEntity levelProject;

    /**
     * Status Project Id
     */
    @ManyToOne
    @JoinColumn(name = "status_project_id", nullable = false)
    private StatusProjectEntity statusProject;

    /**
     * Evaluate Project Id
     */
    @ManyToOne
    @JoinColumn(name = "evaluate_project_id")
    private EvaluateProjectEntity evaluateProject;

    /**
     * Customer Id
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
    
    /**
     * Department ID
     */
    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    /**
     * List Employee Project
     */
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<EmployeeProjectEntity> listEmpProject;

    /**
     * List Issue Project
     */
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<IssueEntity> listIssue;

    /**
     * List Document
     */
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<DocumentEntity> listDocument;

    /**
     * History Members
     */
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<HistoryMemberEntity> listHistoryMembers;

    /**
     * List skill project relationship
     */
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<SkillProjectRelEntity> listSkillProjectRel;

    public ProjectEntity() {

    }

    public ProjectEntity(String projectName, String projectCode, String projectDescription) {
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.projectDescription = projectDescription;
    }

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public int getProjectEffort() {
        return projectEffort;
    }

    public void setProjectEffort(int projectEffort) {
        this.projectEffort = projectEffort;
    }

    public LevelProjectEntity getLevelProject() {
        return levelProject;
    }

    public void setLevelProject(LevelProjectEntity levelProject) {
        this.levelProject = levelProject;
    }

    public StatusProjectEntity getStatusProject() {
        return statusProject;
    }

    public void setStatusProject(StatusProjectEntity statusProject) {
        this.statusProject = statusProject;
    }

    public EvaluateProjectEntity getEvaluateProject() {
        return evaluateProject;
    }

    public void setEvaluateProject(EvaluateProjectEntity evaluateProject) {
        this.evaluateProject = evaluateProject;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public List<EmployeeProjectEntity> getListEmpProject() {
        return listEmpProject;
    }

    public void setListEmpProject(List<EmployeeProjectEntity> listEmpProject) {
        this.listEmpProject = listEmpProject;
    }

    public List<IssueEntity> getListIssue() {
        return listIssue;
    }

    public void setListIssue(List<IssueEntity> listIssue) {
        this.listIssue = listIssue;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public List<DocumentEntity> getListDocument() {
        return listDocument;
    }

    public void setListDocument(List<DocumentEntity> listDocument) {
        this.listDocument = listDocument;
    }

    public List<HistoryMemberEntity> getListHistoryMembers() {
        return listHistoryMembers;
    }

    public void setListHistoryMembers(List<HistoryMemberEntity> listHistoryMembers) {
        this.listHistoryMembers = listHistoryMembers;
    }

    public List<SkillProjectRelEntity> getListSkillProjectRel() {
        return listSkillProjectRel;
    }

    public void setListSkillProjectRel(List<SkillProjectRelEntity> listSkillProjectRel) {
        this.listSkillProjectRel = listSkillProjectRel;
    }

}