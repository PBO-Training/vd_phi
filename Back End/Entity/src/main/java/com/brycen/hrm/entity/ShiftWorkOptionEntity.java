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
 * [Description]:ShiftWorkOption Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "shift_work_option")
public class ShiftWorkOptionEntity extends BaseEntity {
    /**
     * Shift Work Option Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shift_work_option_id")
    private Long shiftWorkOptionID;

    /**
     * Shift Work Option Name
     */
    @Column(name = "shift_work_option_name", nullable = false)
    private String shiftWorkOptionName;

    /**
     * Shift Work Option Code
     */
    @Column(name = "shift_work_option_code", nullable = false)
    private String shiftWorkOptionCode;

    /**
     * Shift Work Option Description
     */
    @Column(name = "shift_work_option_description", nullable = false)
    private String shiftWorkOptionDescription;

    /**
     * Shift Work Option Start Time AM
     */
    @Column(name = "shift_work_option_start_time_am", nullable = false)
    private String shiftWorkOptionStartTimeAM;

    /**
     * Shift Work Option End Time AM
     */
    @Column(name = "shift_work_option_end_time_am", nullable = false)
    private String shiftWorkOptionEndTimeAM;

    /**
     * Shift Work Option Start Time PM
     */
    @Column(name = "shift_work_option_start_time_pm", nullable = false)
    private String shiftWorkOptionStartTimePM;

    /**
     * Shift Work Option End Time PM
     */
    @Column(name = "shift_work_option_end_time_pm", nullable = false)
    private String shiftWorkOptionEndTimePM;

    /**
     * List Shift Work
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shiftWorkOption")
    private Set<ShiftWorkEntity> listShiftWork = new HashSet<>();

    public ShiftWorkOptionEntity() {

    }

    public Long getShiftWorkOptionID() {
        return shiftWorkOptionID;
    }

    public void setShiftWorkOptionID(Long shiftWorkOptionID) {
        this.shiftWorkOptionID = shiftWorkOptionID;
    }

    public String getShiftWorkOptionName() {
        return shiftWorkOptionName;
    }

    public void setShiftWorkOptionName(String shiftWorkOptionName) {
        this.shiftWorkOptionName = shiftWorkOptionName;
    }

    public String getShiftWorkOptionCode() {
        return shiftWorkOptionCode;
    }

    public void setShiftWorkOptionCode(String shiftWorkOptionCode) {
        this.shiftWorkOptionCode = shiftWorkOptionCode;
    }

    public String getShiftWorkOptionDescription() {
        return shiftWorkOptionDescription;
    }

    public void setShiftWorkOptionDescription(String shiftWorkOptionDescription) {
        this.shiftWorkOptionDescription = shiftWorkOptionDescription;
    }

    public String getShiftWorkOptionStartTimeAM() {
        return shiftWorkOptionStartTimeAM;
    }

    public void setShiftWorkOptionStartTimeAM(String shiftWorkOptionStartTimeAM) {
        this.shiftWorkOptionStartTimeAM = shiftWorkOptionStartTimeAM;
    }

    public String getShiftWorkOptionEndTimeAM() {
        return shiftWorkOptionEndTimeAM;
    }

    public void setShiftWorkOptionEndTimeAM(String shiftWorkOptionEndTimeAM) {
        this.shiftWorkOptionEndTimeAM = shiftWorkOptionEndTimeAM;
    }

    public String getShiftWorkOptionStartTimePM() {
        return shiftWorkOptionStartTimePM;
    }

    public void setShiftWorkOptionStartTimePM(String shiftWorkOptionStartTimePM) {
        this.shiftWorkOptionStartTimePM = shiftWorkOptionStartTimePM;
    }

    public String getShiftWorkOptionEndTimePM() {
        return shiftWorkOptionEndTimePM;
    }

    public void setShiftWorkOptionEndTimePM(String shiftWorkOptionEndTimePM) {
        this.shiftWorkOptionEndTimePM = shiftWorkOptionEndTimePM;
    }

}
