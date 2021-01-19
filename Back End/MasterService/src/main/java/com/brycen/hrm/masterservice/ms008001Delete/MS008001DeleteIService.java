package com.brycen.hrm.masterservice.ms008001Delete;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * [Description]: Delete Service for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS008001DeleteIService {

    /**
     * [Description]: Delete list employee status in listStatusEmployeeID with company code is companyID<br/>
     * [ Remarks ]:<br/>
     *
     * @param listStatusEmployeeID
     * @param companyID
     * @return Number of employee status is deleted
     */
    int delete(List<Long> listStatusEmployeeID, int companyID);

}
