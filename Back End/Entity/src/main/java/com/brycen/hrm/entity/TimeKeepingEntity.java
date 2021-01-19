package com.brycen.hrm.entity;

import java.util.Date;

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
@Table(name = "time_keeping")
public class TimeKeepingEntity extends BaseEntity {
    /**
     * TimeKeeping ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timekeeping_id")
    private Long timekeepingID;    

    /**
     * year
     */
    @Column(name = "year", nullable = false)
    private int year;
    
    /**
     * start time keeping
     */
    @Column(name = "start_timekeeping", nullable = false)
    private Date startTimekeeping;
    
    /**
     * end time keeping
     */
    @Column(name = "end_timekeeping", nullable = false)
    private Date endTimekeeping;

    /**
     * description
     */
    @Column(name = "description", nullable = false)
    private String description;
    
    /**
     * is active
     */
    @Column(name = "is_active", nullable = false)
    private boolean isActive;        

    public TimeKeepingEntity() {
        
    }

    public Long getTimekeepingID() {
        return timekeepingID;
    }

    public void setTimekeepingID(Long timekeepingID) {
        this.timekeepingID = timekeepingID;
    }

    public Date getStartTimekeeping() {
        return startTimekeeping;
    }

    public void setStartTimekeeping(Date startTimekeeping) {
        this.startTimekeeping = startTimekeeping;
    }
    
    public Date getEndTimekeeping() {
        return endTimekeeping;
    }

    public void setEndTimekeeping(Date endTimekeeping) {
        this.endTimekeeping = endTimekeeping;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }   
}
