package com.brycen.hrm.masterservice.ms013002Update;

import java.util.Date;

import com.brycen.hrm.entity.HolidayDetailEntity;

public class MS013002UpdateDetailHolidayRequest {
    private Date holidayDetailsDate;
    private String holidayDetailsName;

    public MS013002UpdateDetailHolidayRequest() {
        // TODO Auto-generated constructor stub
    }

    public MS013002UpdateDetailHolidayRequest(HolidayDetailEntity holidayDetailEntity) {
        super();
        this.holidayDetailsDate = holidayDetailEntity.getHolidayDetailDate();
        this.holidayDetailsName = holidayDetailEntity.getHolidayDetailName();
    }

    public Date getHolidayDetailsDate() {
        return holidayDetailsDate;
    }

    public void setHolidayDetailsDate(Date holidayDetailsDate) {
        this.holidayDetailsDate = holidayDetailsDate;
    }

    public String getHolidayDetailsName() {
        return holidayDetailsName;
    }

    public void setHolidayDetailsName(String holidayDetailsName) {
        this.holidayDetailsName = holidayDetailsName;
    }

    

}
