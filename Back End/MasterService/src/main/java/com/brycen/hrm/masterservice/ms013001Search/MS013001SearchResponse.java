package com.brycen.hrm.masterservice.ms013001Search;

import com.brycen.hrm.entity.HolidayEntity;

/**
 * [Description]: Create constructure of List<Object> in ContentResponse. This is data who client want to see<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS013001SearchResponse {
    private long holidayID;
    private String holidayName;
    private int holidayYear;

    public MS013001SearchResponse() {
        super();
    }

    public MS013001SearchResponse(HolidayEntity holidayEntity) {
        super();
        this.holidayID = holidayEntity.getHolidayID();
        this.holidayName = holidayEntity.getHolidayName();
        this.holidayYear = holidayEntity.getHolidayYear();
    }

    public long getHolidayID() {
        return holidayID;
    }

    public void setHolidayID(long holidayID) {
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

}
