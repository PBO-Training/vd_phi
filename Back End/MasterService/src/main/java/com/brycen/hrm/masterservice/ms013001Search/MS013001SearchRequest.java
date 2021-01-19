package com.brycen.hrm.masterservice.ms013001Search;

import com.brycen.hrm.common.base.BaseRequest;

public class MS013001SearchRequest extends BaseRequest {
    /**
     * Holiday code
     */
    private String holidayName;
    /**
     * Holiday name
     */
    private int holidayYear;

    public MS013001SearchRequest() {
        super();
    }

    public MS013001SearchRequest(String holidayName, int holidayYear) {
        super();
        this.holidayName = holidayName;
        this.holidayYear = holidayYear;
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
