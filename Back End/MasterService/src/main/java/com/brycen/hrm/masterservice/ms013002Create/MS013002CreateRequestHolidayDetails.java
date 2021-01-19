package com.brycen.hrm.masterservice.ms013002Create;

import java.sql.Date;

/**
 * [Description]: Create Details Request for Holiday Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS013002CreateRequestHolidayDetails {

    /**
     * Holiday details name
     */
    private String holidayDetailsName;

    /**
     * Holiday details date
     */
    private Date holidayDetailsDate;

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

}
