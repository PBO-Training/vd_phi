package com.brycen.hrm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

@Entity
@Table(name = "history_member")
public class HistoryMemberEntity extends BaseEntity{
    /**
     * History Member Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_member_id")
    private Long historyMemberID;
    /**
     * Id of Employee
     */
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;
    
    /**
     * Project Id
     */
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;
    
    /**
     * Position Project Id
     */
    @ManyToOne
    @JoinColumn(name = "position_project_id")
    private PositionProjectEntity positionProject;
    
    /**
     * Date Join
     */
    @Column(name = "date_join_project")
    private Date dateJoinProject;
    
    /**
     * Date Out
     */
    @Column(name = "date_out_project")
    private Date dateOutProject;

    
    public HistoryMemberEntity() {
        
    }

    public HistoryMemberEntity(Long historyMemberID, EmployeeEntity employee, ProjectEntity project, PositionProjectEntity positionProject,
            Date dateJoinProject, Date dateOutProject) {
        super();
        this.historyMemberID = historyMemberID;
        this.employee = employee;
        this.project = project;
        this.positionProject = positionProject;
        this.dateJoinProject = dateJoinProject;
        this.dateOutProject = dateOutProject;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public PositionProjectEntity getPositionProject() {
        return positionProject;
    }

    public void setPositionProject(PositionProjectEntity positionProject) {
        this.positionProject = positionProject;
    }

    public Long getHistoryMemberID() {
        return historyMemberID;
    }

    public void setHistoryMemberID(Long historyMemberID) {
        this.historyMemberID = historyMemberID;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
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
    
}
