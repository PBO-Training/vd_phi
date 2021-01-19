package com.brycen.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:TimeKeeping Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "time_keeping_rule")
public class TimeKeepingRuleEntity extends BaseEntity {
    /**
     * TimeKeeping ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timekeeping_rule_id")
    private Long timekeepingRuleID;    

    /**
     * min
     */
    @Column(name = "min", nullable = false)
    private int min;
      
    /**
     * max
     */
    @Column(name = "max", nullable = false)
    private int max;
    
    /**
     * minus hour
     */
    @Column(name = "minus_hour", nullable = false)
    private double minusHour;

    /**
     * description
     */
    @Column(name = "description", nullable = false)
    private String description; 
    
    /**
     * violation code
     */
    @Column(name = "violation_code", nullable = false)
    private String violationCode; 
    
    /**
     * is salary
     */
    @Column(name = "is_salary", nullable = false)
    private boolean isSalary; 
    
    /**
     * is loop
     */
    @Column(name = "is_loop", nullable = false)
    private boolean isLoop; 

    public TimeKeepingRuleEntity() {
        
    }

    public Long getTimekeepingRuleID() {
        return timekeepingRuleID;
    }

    public void setTimekeepingRuleID(Long timekeepingRuleID) {
        this.timekeepingRuleID = timekeepingRuleID;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
    
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
    public double getMinusHour() {
        return minusHour;
    }

    public void setMinusHour(double minusHour) {
        this.minusHour = minusHour;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getViolationCode() {
        return violationCode;
    }

    public void setViolationCode(String violationCode) {
        this.violationCode = violationCode;
    }
    
    public boolean getIsSalary() {
        return isSalary;
    }

    public void setIsSalary(boolean isSalary) {
        this.isSalary = isSalary;
    }
    
    public boolean getIsLoop() {
        return isLoop;
    }

    public void setIsLoop(boolean isLoop) {
        this.isLoop = isLoop;
    }
    
}
