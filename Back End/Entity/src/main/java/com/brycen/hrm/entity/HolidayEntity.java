package com.brycen.hrm.entity;

import java.util.ArrayList;
import java.util.List;

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
 * [Description]:VacationList Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "holiday")
public class HolidayEntity extends BaseEntity {
    /**
     * Id of Holiday
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "holiday_id")
    private Long holidayID;

    /**
     * Holiday Name
     */
    @Column(name = "holiday_name", nullable = false)
    private String holidayName;

    /**
     * Holiday Year
     */
    @Column(name = "holiday_year", nullable = false)
    private int holidayYear;

    /**
     * List Vacation Detail
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "holiday")
    private List<HolidayDetailEntity> holidayDetail = new ArrayList<>();

    public HolidayEntity() {

    }

    public HolidayEntity(HolidayEntity holidayEntity) {
        super();
        this.holidayName = holidayEntity.getHolidayName();
        this.holidayYear = holidayEntity.getHolidayYear();
        this.holidayDetail = holidayEntity.getHolidayDetail();
    }

    public Long getHolidayID() {
        return holidayID;
    }

    public void setHolidayID(Long holidayID) {
        this.holidayID = holidayID;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public int getHolidayYear() {
        return holidayYear;
    }

    public void setHolidayYear(int holidayYear) {
        this.holidayYear = holidayYear;
    }

    public List<HolidayDetailEntity> getHolidayDetail() {
        return holidayDetail;
    }

    public void setHolidayDetail(List<HolidayDetailEntity> holidayDetail) {
        this.holidayDetail = holidayDetail;
    }

}
