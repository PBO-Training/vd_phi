package com.brycen.hrm.masterservice.ms013002Create;

import java.util.List;

/**
 * [Description]: Create request for Holiday Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS013002CreateRequest {

    /**
     * Holiday Name
     */
    private String holidayName;

    /**
     * Holiday Year
     */
    private int holidayYear;

    /**
     * List holiday details
     */
    private List<MS013002CreateRequestHolidayDetails> listHolidayDetails;

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

    public List<MS013002CreateRequestHolidayDetails> getListHolidayDetails() {
        return listHolidayDetails;
    }

    public void setListHolidayDetails(List<MS013002CreateRequestHolidayDetails> listHolidayDetails) {
        this.listHolidayDetails = listHolidayDetails;
    }

}
