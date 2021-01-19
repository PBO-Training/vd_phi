package com.brycen.hrm.masterservice.ms010001Delete;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * [Description]: Interface is called by controller to do actions delete a project position<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS010001DeleteIService {
    /**
     * [Description]: Method find project position with id specification and delete it<br/>
     * [ Remarks ]:<br/>
     *
     * @param listPositionProjectIDID
     * @param companyID
     * @return BaseResponse - Model contain data what need to send to client
     */
    int delete(List<Long> positionDelete, int companyID);
}
