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
 * [Description]:VacationListDetail Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "holiday_detail")
public class HolidayDetailEntity extends BaseEntity {
    /**
     * Vacation List Detail Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "holiday_detail_id")
    private Long holidayDetailID;

    /**
     * Vacation List Detail Name
     */
    @Column(name = "holiday_detail_name", nullable = false)
    private String holidayDetailName;

    /**
     * Vacation Detail Date
     */
    @Column(name = "holiday_detail_date", nullable = false)
    private Date holidayDetailDate;

    /**
     * List Vacation
     */
    @ManyToOne
    @JoinColumn(name = "holiday_id", nullable = false)
    private  HolidayEntity holiday;

    public HolidayDetailEntity() {

    }

    public HolidayDetailEntity(String holidayDetailName, Date holidayDetailDate, HolidayEntity holiday) {
        super();
        this.holidayDetailName = holidayDetailName;
        this.holidayDetailDate = holidayDetailDate;
        this.holiday = holiday;
    }

    public Long getHolidayDetailID() {
        return holidayDetailID;
    }

    public void setHolidayDetailID(Long holidayDetailID) {
        this.holidayDetailID = holidayDetailID;
    }

    public String getHolidayDetailName() {
        return holidayDetailName;
    }

    public void setHolidayDetailName(String holidayDetailName) {
        this.holidayDetailName = holidayDetailName;
    }

    public Date getHolidayDetailDate() {
        return holidayDetailDate;
    }

    public void setHolidayDetailDate(Date holidayDetailDate) {
        this.holidayDetailDate = holidayDetailDate;
    }

    public HolidayEntity getHoliday() {
        return holiday;
    }

    public void setHoliday(HolidayEntity holiday) {
        this.holiday = holiday;
    }

}
