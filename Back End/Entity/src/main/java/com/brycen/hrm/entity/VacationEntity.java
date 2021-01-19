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

/**
 * [Description]:Vacation Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "vacation")
public class VacationEntity extends BaseEntity {
    /**
     * ID of Vacation
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_id")
    private Long vacationID;

    /**
     * Reason of Vacation
     */
    @Column(name = "vacation_reason")
    private String vacationReason;

    /**
     * Note1 of Vacation
     */
    @Column(name = "vacation_group_one_note")
    private String vacationGroupOneNote;

    /**
     * Note2 of Vacation
     */
    @Column(name = "vacation_group_two_note")
    private String vacationGroupTwoNote;

    /**
     * Note3 of Vacation
     */
    @Column(name = "vacation_group_three_note")
    private String vacationGroupThreeNote;
        
    /**
     * vacation Update GroupOne Date
     */
    @Column(name = "update_group_one_date")
    private Date vacationUpdateGroupOneDate;
    
    /**
     * vacation Update GroupTwo Date
     */
    @Column(name = "update_group_two_date")
    private Date vacationUpdateGroupTwoDate;
    
    /**
     * vacation Update GroupThree Date
     */
    @Column(name = "update_group_three_date")
    private Date vacationUpdateGroupThreeDate;

    /**
     * Vacation Start Date
     */
    @Column(name = "vacation_start_date")
    private Date vacationStartDate;

    /**
     * Vacation End Date
     */
    @Column(name = "vacation_end_date")
    private Date vacationEndDate;

    /**
     * Group Assign 1
     */
    @Column(name = "group_assign_one")
    private String groupAssignOne;

    /**
     * Group Assign 2
     */
    @Column(name = "group_assign_two")
    private String groupAssignTwo;

    /**
     * Group Assign 3
     */
    @Column(name = "group_assign_three")
    private String groupAssignThree;

    /**
     * Vacation Type
     */
    @ManyToOne
    @JoinColumn(name = "vacation_type_id", nullable = false)
    private VacationTypeEntity vacationType;

    /**
     * Option hour in a day
     */
    @ManyToOne
    @JoinColumn(name = "vacation_option_id", nullable = false)
    private VacationOptionEntity vacationOption;

    /**
     * Employee is registration
     */
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    /**
     * Status of vacation
     */
    @ManyToOne
    @JoinColumn(name = "vacation_status_id", nullable = false)
    private VacationStatusEntity status;
    
    @Column(name = "leave_hour")
    private float leaveHour;
    
    @Column(name = "time_work")
    private float timeWork;

    public VacationEntity() {

    }

    public Long getVacationID() {
        return vacationID;
    }

    public void setVacationTypeID(Long vacationID) {
        this.vacationID = vacationID;
    }

    public String getVacationReason() {
        return vacationReason;
    }

    public void setVacationReason(String vacationReason) {
        this.vacationReason = vacationReason;
    }

    public String getVacationGroupOneNote() {
        return vacationGroupOneNote;
    }

    public void setVacationGroupOneNote(String vacationGroupOneNote) {
        this.vacationGroupOneNote = vacationGroupOneNote;
    }

    public String getVacationGroupTwoNote() {
        return vacationGroupTwoNote;
    }

    public void setVacationGroupTwoNote(String vacationGroupTwoNote) {
        this.vacationGroupTwoNote = vacationGroupTwoNote;
    }

    public String getVacationGroupThreeNote() {
        return vacationGroupThreeNote;
    }

    public void setVacationGroupThreeNote(String vacationGroupThreeNote) {
        this.vacationGroupThreeNote = vacationGroupThreeNote;
    }
    
    public Date getVacationStartDate() {
        return vacationStartDate;
    }

    public void setVacationStartDate(Date vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }
    
    public Date getVacationUpdateGroupOneDate() {
        return vacationUpdateGroupOneDate;
    }

    public void setVacationUpdateGroupOneDate(Date vacationUpdateGroupOneDate) {
        this.vacationUpdateGroupOneDate = vacationUpdateGroupOneDate;
    }
    
    public Date getVacationUpdateGroupTwoDate() {
        return vacationUpdateGroupTwoDate;
    }

    public void setVacationUpdateGroupTwoDate(Date vacationUpdateGroupTwoDate) {
        this.vacationUpdateGroupTwoDate = vacationUpdateGroupTwoDate;
    }

    public Date getVacationUpdateGroupThreeDate() {
        return vacationUpdateGroupThreeDate;
    }

    public void setVacationUpdateGroupThreeDate(Date vacationUpdateGroupThreeDate) {
        this.vacationUpdateGroupThreeDate = vacationUpdateGroupThreeDate;
    }

    public Date getVacationEndDate() {
        return vacationEndDate;
    }

    public void setVacationEndDate(Date vacationEndDate) {
        this.vacationEndDate = vacationEndDate;
    }

    public String getGroupAssignOne() {
        return groupAssignOne;
    }

    public void setGroupAssignOne(String groupAssignOne) {
        this.groupAssignOne = groupAssignOne;
    }

    public String getGroupAssignTwo() {
        return groupAssignTwo;
    }

    public void setGroupAssignTwo(String groupAssignTwo) {
        this.groupAssignTwo = groupAssignTwo;
    }

    public String getGroupAssignThree() {
        return groupAssignThree;
    }

    public void setGroupAssignThree(String groupAssignThree) {
        this.groupAssignThree = groupAssignThree;
    }

    public VacationTypeEntity getVacationType() {
        return vacationType;
    }

    public void setVacationType(VacationTypeEntity vacationType) {
        this.vacationType = vacationType;
    }

    public void setVacationID(Long vacationID) {
        this.vacationID = vacationID;
    }

    public VacationOptionEntity getVacationOption() {
        return vacationOption;
    }

    public void setVacationOption(VacationOptionEntity vacationOption) {
        this.vacationOption = vacationOption;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public VacationStatusEntity getStatus() {
        return status;
    }

    public void setStatus(VacationStatusEntity status) {
        this.status = status;
    }
    
    public float getLeaveHour() {
        return leaveHour;
    }

    public void setLeaveHour(float leaveHour) {
        this.leaveHour = leaveHour;        
    }
    
    public float getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(float timeWork) {
        this.timeWork = timeWork;        
    }

}
