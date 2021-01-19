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
 * [Description]:ShiftWork Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
/**
 * [Description]:<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "shift_work")
public class ShiftWorkEntity extends BaseEntity {
    /**
     * Shift Work Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shift_work_id")
    private Long shiftWorkID;

    /**
     * Shift Work Start Date AM
     */
    @Column(name = "shift_work_start_date_am", nullable = false)
    private Date shiftWorkStartDateAM;

    /**
     * Shift Work End Date AM
     */
    @Column(name = "shift_work_end_date_am", nullable = false)
    private Date shiftWorkEndDateAM;

    /**
     * Shift Work Start Date PM
     */
    @Column(name = "shift_work_start_date_pm", nullable = false)
    private Date shiftWorkStartDatePM;

    /**
     * Shift Work End Date PM
     */
    @Column(name = "shift_work_end_date_pm", nullable = false)
    private Date shiftWorkEndDatePM;

    /**
     * Shift Work Option ID
     */
    @ManyToOne
    @JoinColumn(name = "shift_work_option_id", nullable = false)
    private ShiftWorkOptionEntity shiftWorkOption;

    /**
     * Shift Work Reason
     */
    @Column(name = "shift_work_reason", nullable = false)
    private String shiftWorkReason;

    /**
     * Shift Work Employee ID
     */
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    /**
     * Shift Work Status ID
     */
    @ManyToOne
    @JoinColumn(name = "shift_work_status_id", nullable = false)
    private VacationStatusEntity status;

    /**
     * Assign Group One
     */
    @Column(name = "shift_work_assign_group_one", nullable = false)
    private String shiftWorkAssignGroupOne;

    /**
     * Assign Group Two
     */
    @Column(name = "shift_work_assign_group_two", nullable = false)
    private String shiftWorkAssignGroupTwo;

    /**
     * Assign Group Three
     */
    @Column(name = "shift_work_assign_group_three", nullable = false)
    private String shiftWorkAssignGroupThree;

    /**
     * Assign Group One Note
     */
    @Column(name = "shift_work_assign_group_one_note")
    private String shiftWorkAssignGroupOneNote;

    /**
     * Assign Group Two Note
     */
    @Column(name = "shift_work_assign_group_two_note")
    private String shiftWorkAssignGroupTwoNote;

    /**
     * Assign Group Three Note
     */
    @Column(name = "shift_work_assign_group_three_note")
    private String shiftWorkAssignGroupThreeNote;

    /**
     * Assign Group One Update Date
     */
    @Column(name = "shift_work_update_group_one_date")
    private Date shiftWorkUpdateGroupOneDate;

    /**
     * Assign Group Two Update Date
     */
    @Column(name = "shift_work_update_group_two_date")
    private Date shiftWorkUpdateGroupTwoDate;

    /**
     * Assign Group Three Update Date
     */
    @Column(name = "shift_work_update_group_three_date")
    private Date shiftWorkUpdateGroupThreeDate;

    public ShiftWorkEntity() {

    }

    public Long getShiftWorkID() {
        return shiftWorkID;
    }

    public void setShiftWorkID(Long shiftWorkID) {
        this.shiftWorkID = shiftWorkID;
    }

    public Date getShiftWorkStartDateAM() {
        return shiftWorkStartDateAM;
    }

    public void setShiftWorkStartDateAM(Date shiftWorkStartDateAM) {
        this.shiftWorkStartDateAM = shiftWorkStartDateAM;
    }

    public Date getShiftWorkEndDateAM() {
        return shiftWorkEndDateAM;
    }

    public void setShiftWorkEndDateAM(Date shiftWorkEndDateAM) {
        this.shiftWorkEndDateAM = shiftWorkEndDateAM;
    }

    public Date getShiftWorkStartDatePM() {
        return shiftWorkStartDatePM;
    }

    public void setShiftWorkStartDatePM(Date shiftWorkStartDatePM) {
        this.shiftWorkStartDatePM = shiftWorkStartDatePM;
    }

    public Date getShiftWorkEndDatePM() {
        return shiftWorkEndDatePM;
    }

    public void setShiftWorkEndDatePM(Date shiftWorkEndDatePM) {
        this.shiftWorkEndDatePM = shiftWorkEndDatePM;
    }

    public ShiftWorkOptionEntity getShiftWorkOption() {
        return shiftWorkOption;
    }

    public void setShiftWorkOption(ShiftWorkOptionEntity shiftWorkOption) {
        this.shiftWorkOption = shiftWorkOption;
    }

    public String getShiftWorkReason() {
        return shiftWorkReason;
    }

    public void setShiftWorkReason(String shiftWorkReason) {
        this.shiftWorkReason = shiftWorkReason;
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

    public String getShiftWorkAssignGroupOne() {
        return shiftWorkAssignGroupOne;
    }

    public void setShiftWorkAssignGroupOne(String shiftWorkAssignGroupOne) {
        this.shiftWorkAssignGroupOne = shiftWorkAssignGroupOne;
    }

    public String getShiftWorkAssignGroupTwo() {
        return shiftWorkAssignGroupTwo;
    }

    public void setShiftWorkAssignGroupTwo(String shiftWorkAssignGroupTwo) {
        this.shiftWorkAssignGroupTwo = shiftWorkAssignGroupTwo;
    }

    public String getShiftWorkAssignGroupThree() {
        return shiftWorkAssignGroupThree;
    }

    public void setShiftWorkAssignGroupThree(String shiftWorkAssignGroupThree) {
        this.shiftWorkAssignGroupThree = shiftWorkAssignGroupThree;
    }

    public String getShiftWorkAssignGroupOneNote() {
        return shiftWorkAssignGroupOneNote;
    }

    public void setShiftWorkAssignGroupOneNote(String shiftWorkAssignGroupOneNote) {
        this.shiftWorkAssignGroupOneNote = shiftWorkAssignGroupOneNote;
    }

    public String getShiftWorkAssignGroupTwoNote() {
        return shiftWorkAssignGroupTwoNote;
    }

    public void setShiftWorkAssignGroupTwoNote(String shiftWorkAssignGroupTwoNote) {
        this.shiftWorkAssignGroupTwoNote = shiftWorkAssignGroupTwoNote;
    }

    public String getShiftWorkAssignGroupThreeNote() {
        return shiftWorkAssignGroupThreeNote;
    }

    public void setShiftWorkAssignGroupThreeNote(String shiftWorkAssignGroupThreeNote) {
        this.shiftWorkAssignGroupThreeNote = shiftWorkAssignGroupThreeNote;
    }

    public Date getShiftWorkUpdateGroupOneDate() {
        return shiftWorkUpdateGroupOneDate;
    }

    public void setShiftWorkUpdateGroupOneDate(Date shiftWorkUpdateGroupOneDate) {
        this.shiftWorkUpdateGroupOneDate = shiftWorkUpdateGroupOneDate;
    }

    public Date getShiftWorkUpdateGroupTwoDate() {
        return shiftWorkUpdateGroupTwoDate;
    }

    public void setShiftWorkUpdateGroupTwoDate(Date shiftWorkUpdateGroupTwoDate) {
        this.shiftWorkUpdateGroupTwoDate = shiftWorkUpdateGroupTwoDate;
    }

    public Date getShiftWorkUpdateGroupThreeDate() {
        return shiftWorkUpdateGroupThreeDate;
    }

    public void setShiftWorkUpdateGroupThreeDate(Date shiftWorkUpdateGroupThreeDate) {
        this.shiftWorkUpdateGroupThreeDate = shiftWorkUpdateGroupThreeDate;
    }

}
