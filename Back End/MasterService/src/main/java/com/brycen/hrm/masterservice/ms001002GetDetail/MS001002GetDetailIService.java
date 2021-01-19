package com.brycen.hrm.masterservice.ms001002GetDetail;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Get Details Service for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS001002GetDetailIService {

    /**
     * [Description]: Find an user by ID<br/>
     * [ Remarks ]:<br/>
     *
     * @param userID
     * @param companyID
     * @return An user details
     */
    BaseResponse findByID(long userID, int companyID);

}
