package com.brycen.hrm.masterservice.ms007001Delete;

import java.util.List;

/**
 * [Description]: Interface is called by controller to do actions delete a employee position<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */

import org.springframework.stereotype.Service;

@Service
public interface MS007001DeleteIService {
    /**
     * [Description]: Method find employee position with id specification and delete it<br/>
     * [ Remarks ]:<br/>
     *
     * @param listEmployeePositionDeleteID
     * @param companyID
     * @return BaseResponse - Model contain data what need to send to client
     */
    int delete(List<Long> listEmployeePositionDeleteID, int companyID);
}
