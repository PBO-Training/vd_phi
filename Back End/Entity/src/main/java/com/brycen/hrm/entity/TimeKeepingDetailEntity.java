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
 * [Description]:TimeKeepingListDetail Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "time_keeping_detail")
public class TimeKeepingDetailEntity extends BaseEntity {
    /**
     * time keeping detail Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_keeping_detail_id")
    private Long timeKeepingDetailID;

    /**
     * Employee Code
     */
    @Column(name = "employee_code", length = 40, nullable = false)
    private String employeeCode;
    
    /**
     * Employee Name
     */
    @Column(name = "employee_name", nullable = false)
    private String employeeName;
    
    /**
     * Employee ID
     */
    @Column(name = "employee_id", nullable = false)
    private long employeeID;

    /**
     * Date
     */
    @Column(name = "date", nullable = false)
    private Date date;
    
    /**
     * Violation
     */
    @Column(name = "violation", nullable = false)
    private String violation;
    
    /**
     * Check In AM
     */
    @Column(name = "checkin_am", nullable = true)
    private String checkInAM;
    
    /**
     * Check Out AM
     */
    @Column(name = "checkout_am", nullable = true)
    private String checkOutAM;
    
    /**
     * Check In PM
     */
    @Column(name = "checkin_pm", nullable = true)
    private String checkInPM;
    
    /**
     * Check Out PM
     */
    @Column(name = "checkout_pm", nullable = true)
    private String checkOutPM;

    /**
     * List Time keeping
     */
    @ManyToOne
    @JoinColumn(name = "timekeeping_id", nullable = false)
    private  TimeKeepingEntity timekeepingID;
    
    /**
     * minus hour
     */
    @Column(name = "minus_hour", nullable = true)
    private double minusHour;
    
    /**
     * times
     */
    @Column(name = "times", nullable = true)
    private int times;
    
    /**
     * start date
     */
    @Column(name = "start_date", nullable = true)
    private Date startDate;
    
    /**
     * end date
     */
    @Column(name = "end_date", nullable = true)
    private Date endDate;
    
    /**
     * is salary
     */
    @Column(name = "is_salary", nullable = false)
    private boolean isSalary;

    public TimeKeepingDetailEntity() {

    }   

    public Long getTimeKeepingDetailID() {
        return timeKeepingDetailID;
    }

    public void setTimeKeepingDetailID(Long timeKeepingDetailID) {
        this.timeKeepingDetailID = timeKeepingDetailID;
    } 
    
    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    } 
    
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    } 
    
    public long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(long employeeID) {
        this.employeeID = employeeID;
    } 
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    } 
    
    public String getViolation() {
        return violation;
    }

    public void setViolation(String violation) {
        this.violation = violation;
    } 
    
    public String getCheckInAM() {
        return checkInAM;
    }

    public void setCheckInAM(String checkInAM) {
        this.checkInAM = checkInAM;
    } 
    
    public String getCheckOutAM() {
        return checkOutAM;
    }

    public void setCheckOutAM(String checkOutAM) {
        this.checkOutAM = checkOutAM;
    } 
    
    public String getCheckInPM() {
        return checkInPM;
    }

    public void setCheckInPM(String checkInPM) {
        this.checkInPM = checkInPM;
    }
    
    public String getCheckOutPM() {
        return checkOutPM;
    }

    public void setCheckOutPM(String checkOutPM) {
        this.checkOutPM = checkOutPM;
    } 

    public TimeKeepingEntity getTimeKeeping() {
        return timekeepingID;
    }

    public void setTimeKeeping(TimeKeepingEntity timekeepingID) {
        this.timekeepingID = timekeepingID;
    }
    
    public double getMinusHour() {
        return minusHour;
    }

    public void setMinusHour(double minusHour) {
        this.minusHour = minusHour;
    }  
    
    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
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
    
    public boolean getIsSalary() {
        return isSalary;
    }

    public void setIsSalary(boolean isSalary) {
        this.isSalary = isSalary;
    } 

}
