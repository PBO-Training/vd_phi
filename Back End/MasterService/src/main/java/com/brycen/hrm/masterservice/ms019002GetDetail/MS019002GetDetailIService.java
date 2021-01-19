package com.brycen.hrm.masterservice.ms019002GetDetail;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Get Details Service for Scope Work Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS019002GetDetailIService {

    /**
     * [Description]: Get Details a Scope Work<br/>
     * [ Remarks ]:<br/>
     *
     * @param statusEmployeeID
     * @param companyID
     * @return a Scope Work details
     */
    BaseResponse getDetail(long statusEmployeeID, int companyID);

}
