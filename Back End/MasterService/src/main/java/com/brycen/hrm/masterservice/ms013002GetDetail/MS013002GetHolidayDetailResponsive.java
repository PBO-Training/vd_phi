package com.brycen.hrm.masterservice.ms013002GetDetail;

import java.util.Date;

import com.brycen.hrm.entity.HolidayDetailEntity;

public class MS013002GetHolidayDetailResponsive {
    // private Long holidayDetailID;
    private String holidayDetailsName;
    private Date holidayDetailsDate;

    public MS013002GetHolidayDetailResponsive() {
        // TODO Auto-generated constructor stub
    }

    public MS013002GetHolidayDetailResponsive(HolidayDetailEntity holidayDetailEntity) {
        super();
        this.holidayDetailsName = holidayDetailEntity.getHolidayDetailName();
        this.holidayDetailsDate = holidayDetailEntity.getHolidayDetailDate();
    }

    public String getHolidayDetailsName() {
        return holidayDetailsName;
    }

    public void setHolidayDetailsName(String holidayDetailsName) {
        this.holidayDetailsName = holidayDetailsName;
    }

    public Date getHolidayDetailsDate() {
        return holidayDetailsDate;
    }

    public void setHolidayDetailsDate(Date holidayDetailsDate) {
        this.holidayDetailsDate = holidayDetailsDate;
    }

    // public Long getHolidayDetailID() {
    // return holidayDetailID;
    // }
    //
    // public void setHolidayDetailID(Long holidayDetailID) {
    // this.holidayDetailID = holidayDetailID;
    // }

}
