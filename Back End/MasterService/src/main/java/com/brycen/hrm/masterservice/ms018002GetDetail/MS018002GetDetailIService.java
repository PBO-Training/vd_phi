package com.brycen.hrm.masterservice.ms018002GetDetail;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Get Details Service for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS018002GetDetailIService {

    /**
     * [Description]: Get Details a role<br/>
     * [ Remarks ]:<br/>
     *
     * @param statusEmployeeID
     * @param companyID
     * @return a role details
     */
    BaseResponse getDetail(long statusEmployeeID, int companyID);

}
