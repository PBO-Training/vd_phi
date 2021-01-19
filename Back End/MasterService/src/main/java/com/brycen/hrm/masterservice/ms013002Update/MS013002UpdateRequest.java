package com.brycen.hrm.masterservice.ms013002Update;

import java.util.List;

public class MS013002UpdateRequest {
    private Long holidayID;
    private String holidayName;
    private int holidayYear;
    private List<MS013002UpdateDetailHolidayRequest> listHolidayDetails;

    public MS013002UpdateRequest() {
        // TODO Auto-generated constructor stub
    }

    public MS013002UpdateRequest(Long holidayID, String holidayName, int holidayYear) {
        super();
        this.holidayID = holidayID;
        this.holidayName = holidayName;
        this.holidayYear = holidayYear;
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

    public List<MS013002UpdateDetailHolidayRequest> getListHolidayDetails() {
        return listHolidayDetails;
    }

    public void setListHolidayDetails(List<MS013002UpdateDetailHolidayRequest> listHolidayDetails) {
        this.listHolidayDetails = listHolidayDetails;
    }



}
