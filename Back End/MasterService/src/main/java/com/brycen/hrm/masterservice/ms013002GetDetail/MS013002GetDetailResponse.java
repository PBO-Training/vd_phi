package com.brycen.hrm.masterservice.ms013002GetDetail;

import java.util.List;
import java.util.stream.Collectors;

import com.brycen.hrm.entity.HolidayEntity;

public class MS013002GetDetailResponse {
    private long holidayID;
    private String holidayName;
    private int holidayYear;
    private List<MS013002GetHolidayDetailResponsive> listHolidayDetails;

    public MS013002GetDetailResponse() {
        super();
    }

    public MS013002GetDetailResponse(HolidayEntity holidayEntity) {
        super();
        this.holidayID = holidayEntity.getHolidayID();
        this.holidayName = holidayEntity.getHolidayName();
        this.holidayYear = holidayEntity.getHolidayYear();
        this.listHolidayDetails = holidayEntity.getHolidayDetail().stream().map(MS013002GetHolidayDetailResponsive::new).collect(Collectors.toList());
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

    public List<MS013002GetHolidayDetailResponsive> getListHolidayDetails() {
        return listHolidayDetails;
    }

    public void setListHolidayDetails(List<MS013002GetHolidayDetailResponsive> listHolidayDetails) {
        this.listHolidayDetails = listHolidayDetails;
    }

}
