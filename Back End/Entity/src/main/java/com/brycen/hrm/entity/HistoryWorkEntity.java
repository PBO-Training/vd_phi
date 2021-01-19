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
 * [Description]:HistoryWork Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "history_work")
public class HistoryWorkEntity extends BaseEntity {
    /**
     * History Work Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_work_id")
    private Long historyWorkID;

    /**
     * History Project Name
     */
    @Column(name = "history_project_name")
    private String historyProjectName;

    /**
     * History Company Name
     */
    @Column(name = "history_company_name")
    private String historyCompanyName;

    /**
     * Start Date
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * End Date
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * Inside Company
     */
    @Column(name = "inside_company")
    private boolean insideCompany;

    /**
     * History Tool Develop
     */
    @Column(name = "history_tools")
    private String historyTools;

    /**
     * History Description About Project
     */
    @Column(name = "history_description", length = 2000)
    private String historyDescription;

    /**
     * Complete Review
     */
    @Column(name = "complete_review", columnDefinition = "boolean default false")
    private boolean completeReview;

    /**
     * Operation System Id
     */
    @ManyToOne
    @JoinColumn(name = "operation_system_id")
    private OperationSystemEntity operationSystem;

    /**
     * Used DataBase History Id
     */
    @ManyToOne
    @JoinColumn(name = "database_history_id")
    private DatabaseHistoryEntity databaseHistory;

    /**
     * Employee Id
     */
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    /**
     * Project Id
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    /**
     * History Evaluate Project
     */
    @ManyToOne
    @JoinColumn(name = "evaluate_employee_project_id", nullable = false)
    private EvaluateEmployeeProjectEntity evaluateEmployeeProject;

    /**
     * Position Project Id
     */
    @ManyToOne
    @JoinColumn(name = "position_project_id", nullable = false)
    private PositionProjectEntity positionProject;

    /**
     * List History Technical Skill
     */
    @OneToMany(mappedBy = "historyWork", cascade = CascadeType.ALL)
    private List<HistorySkillEntity> listSkill;

    /**
     * List Process Project
     */
    @OneToMany(mappedBy = "historyWork", cascade = CascadeType.ALL)
    private List<HistoryScopeWorkEntity> listHistoryScopeWork;

    public HistoryWorkEntity() {

    }

    public Long getHistoryWorkID() {
        return historyWorkID;
    }

    public void setHistoryWorkID(Long historyWorkID) {
        this.historyWorkID = historyWorkID;
    }

    public String getHistoryProjectName() {
        return historyProjectName;
    }

    public void setHistoryProjectName(String historyProjectName) {
        this.historyProjectName = historyProjectName;
    }

    public String getHistoryCompanyName() {
        return historyCompanyName;
    }

    public void setHistoryCompanyName(String historyCompanyName) {
        this.historyCompanyName = historyCompanyName;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public List<HistoryScopeWorkEntity> getListHistoryScopeWork() {
        return listHistoryScopeWork;
    }

    public void setListHistoryScopeWork(List<HistoryScopeWorkEntity> listHistoryScopeWork) {
        this.listHistoryScopeWork = listHistoryScopeWork;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isInsideCompany() {
        return insideCompany;
    }

    public void setInsideCompany(boolean insideCompany) {
        this.insideCompany = insideCompany;
    }

    public List<HistorySkillEntity> getListSkill() {
        return listSkill;
    }

    public void setListSkill(List<HistorySkillEntity> listSkill) {
        this.listSkill = listSkill;
    }

    public String getHistoryTools() {
        return historyTools;
    }

    public void setHistoryTools(String historyTools) {
        this.historyTools = historyTools;
    }

    public OperationSystemEntity getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(OperationSystemEntity operationSystem) {
        this.operationSystem = operationSystem;
    }

    public DatabaseHistoryEntity getDatabaseHistory() {
        return databaseHistory;
    }

    public void setDatabaseHistory(DatabaseHistoryEntity databaseHistory) {
        this.databaseHistory = databaseHistory;
    }

    public PositionProjectEntity getPositionProject() {
        return positionProject;
    }

    public void setPositionProject(PositionProjectEntity positionProject) {
        this.positionProject = positionProject;
    }

    public String getHistoryDescription() {
        return historyDescription;
    }

    public void setHistoryDescription(String historyDescription) {
        this.historyDescription = historyDescription;
    }

    public EvaluateEmployeeProjectEntity getEvaluateEmployeeProject() {
        return evaluateEmployeeProject;
    }

    public void setEvaluateEmployeeProject(EvaluateEmployeeProjectEntity evaluateEmployeeProject) {
        this.evaluateEmployeeProject = evaluateEmployeeProject;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public boolean isCompleteReview() {
        return completeReview;
    }

    public void setCompleteReview(boolean completeReview) {
        this.completeReview = completeReview;
    }
}
