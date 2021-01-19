package com.brycen.hrm.masterservice.ms001001Init;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

/**
 * [Description]: Init Service for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS001001InitIService {

    /**
     * [Description]: Initialize data for drop down list in User Master Table<br/>
     * [ Remarks ]:<br/>
     *
     * @param companyID
     * @param req
     * @return data of drop down list
     */
    MS001001InitResponse init(int companyID, HttpServletRequest req);

}
