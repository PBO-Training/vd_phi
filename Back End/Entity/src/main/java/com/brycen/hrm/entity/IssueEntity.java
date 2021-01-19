package com.brycen.hrm.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:Issue Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "issue")
public class IssueEntity extends BaseEntity {
    /**
     * Issue Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id")
    private Long issueID;

    /**
     * Issue Subject
     */
    @Column(name = "issue_subject", nullable = false)
    private String issueSubject;

    /**
     * Issue Description
     */
    @Column(name = "issue_description", length = 2000)
    private String issueDescription;

    /**
     * Start Date Plan
     */
    @Column(name = "issue_start_date_plan")
    private Date issueStartDatePlan;

    /**
     * End Date Plan
     */
    @Column(name = "issue_end_date_plan")
    private Date issueEndDatePlan;

    /**
     * Estimate Time
     */
    @Column(name = "estiamted_time")
    private int estimatedTime;

    /**
     * Percent Complete
     */
    @Column(name = "percent_done")
    private float percentDone;

    /**
     * Amount in plan
     */
    @Column(name = "issue_amount_plan")
    private int issueAmountPlan;

    /**
     * Quality in Plan
     */
    @Column(name = "issue_quality_plan")
    private float issueQualityPlan;

    /**
     * Start Date actual
     */
    @Column(name = "issue_start_date_actual")
    private Date issueStartDateActual;

    /**
     * End Date Reality
     */
    @Column(name = "issue_end_date_actual")
    private Date issueEndDateActual;

    /**
     * Number in actual
     */
    @Column(name = "issue_amount_actual")
    private int issueAmountActual;

    /**
     * Quality in actual
     */
    @Column(name = "issue_quality_actual")
    private float issueQualityActual;

    /**
     * Status Issue Id
     */
    @ManyToOne
    @JoinColumn(name = "status_issue_id", nullable = false)
    private StatusIssueEntity statusIssue;

    /**
     * Priority Issue Id
     */
    @ManyToOne
    @JoinColumn(name = "priority_issue_id", nullable = false)
    private PriorityIssueEntity priorityIssue;

    /**
     * Project Id
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    /**
     * Tracker Id
     */
    @ManyToOne
    @JoinColumn(name = "tracker_id", nullable = false)
    private TrackerEntity tracker;

    /**
     * Employee added issue
     */
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    /**
     * List File Issue
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "issue")
    private Set<FileIssueEntity> listFileIssue = new HashSet<>();

    /**
     * List history Issue updated
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "issue")
    private Set<HistoryUpdateIssue> listHistoryUpdateIssue = new HashSet<>();

    public IssueEntity() {

    }

    public Long getIssueID() {
        return issueID;
    }

    public void setIssueID(Long issueID) {
        this.issueID = issueID;
    }

    public String getIssueSubject() {
        return issueSubject;
    }

    public void setIssueSubject(String issueSubject) {
        this.issueSubject = issueSubject;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public Date getIssueStartDatePlan() {
        return issueStartDatePlan;
    }

    public void setIssueStartDatePlan(Date startDatePlan) {
        this.issueStartDatePlan = startDatePlan;
    }

    public Date getIssueEndDatePlan() {
        return issueEndDatePlan;
    }

    public void setIssueEndDatePlan(Date endDatePlan) {
        this.issueEndDatePlan = endDatePlan;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public float getPercentDone() {
        return percentDone;
    }

    public void setPercentDone(float percentDone) {
        this.percentDone = percentDone;
    }

    public int getIssueAmountPlan() {
        return issueAmountPlan;
    }

    public void setIssueAmountPlan(int numberTargetPlan) {
        this.issueAmountPlan = numberTargetPlan;
    }

    public float getIssueQualityPlan() {
        return issueQualityPlan;
    }

    public void setIssueQualityPlan(float qualityTargetPlan) {
        this.issueQualityPlan = qualityTargetPlan;
    }

    public Date getIssueStartDateActual() {
        return issueStartDateActual;
    }

    public void setIssueStartDateActual(Date startDateReality) {
        this.issueStartDateActual = startDateReality;
    }

    public Date getIssueEndDateActual() {
        return issueEndDateActual;
    }

    public void setIssueEndDateActual(Date endDateReality) {
        this.issueEndDateActual = endDateReality;
    }

    public int getIssueAmountActual() {
        return issueAmountActual;
    }

    public void setIssueAmountActual(int numberTargetReality) {
        this.issueAmountActual = numberTargetReality;
    }

    public float getIssueQualityActual() {
        return issueQualityActual;
    }

    public void setIssueQualityActual(float qualityTargetReality) {
        this.issueQualityActual = qualityTargetReality;
    }

    public StatusIssueEntity getStatusIssue() {
        return statusIssue;
    }

    public void setStatusIssue(StatusIssueEntity statusIssue) {
        this.statusIssue = statusIssue;
    }

    public PriorityIssueEntity getPriorityIssue() {
        return priorityIssue;
    }

    public void setPriorityIssue(PriorityIssueEntity priorityIssue) {
        this.priorityIssue = priorityIssue;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public TrackerEntity getTracker() {
        return tracker;
    }

    public void setTracker(TrackerEntity tracker) {
        this.tracker = tracker;
    }

    public Set<FileIssueEntity> getListFileIssue() {
        return listFileIssue;
    }

    public void setListFileIssue(Set<FileIssueEntity> listFileIssue) {
        this.listFileIssue = listFileIssue;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public Set<HistoryUpdateIssue> getListHistoryUpdateIssue() {
        return listHistoryUpdateIssue;
    }

    public void setListHistoryUpdateIssue(Set<HistoryUpdateIssue> listHistoryUpdateIssue) {
        this.listHistoryUpdateIssue = listHistoryUpdateIssue;
    }

}
