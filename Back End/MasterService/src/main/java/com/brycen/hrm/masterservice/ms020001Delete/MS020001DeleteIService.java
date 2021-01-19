package com.brycen.hrm.masterservice.ms020001Delete;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * [Description]: Interface is called by controller to do actions delete a Degree<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS020001DeleteIService {
    /**
     * [Description]: Method find Degree with id specification and delete it<br/>
     * [ Remarks ]:<br/>
     *
     * @param listvacationTypeID
     * @param companyID
     * @return BaseResponse - Model contain data what need to send to client
     */
    int delete(List<Long> degreeDelete, int companyID);
}
