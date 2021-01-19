package com.brycen.hrm.masterservice.ms001001Delete;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * [Description]: Delete Service for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS001001DeleteIService {

    /**
     * [Description]: Delete list user in userDelete with company code is companyID<br/>
     * [ Remarks ]:<br/>
     *
     * @param userDelete
     * @param companyID
     * @return Number of user is deleted
     */
    int deleteListUser(List<Long> userDelete, int companyID);

}
