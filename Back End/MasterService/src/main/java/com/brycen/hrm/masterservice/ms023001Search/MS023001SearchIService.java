package com.brycen.hrm.masterservice.ms023001Search;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Search Service for ShiftWork Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS023001SearchIService {

    /**
     * [Description]: Search a list ShiftWork<br/>
     * [ Remarks ]:<br/>
     *
     * @param searchRequest
     * @param companyID
     * @return List ShiftWork
     */
    BaseResponse search(MS023001SearchRequest searchRequest, int companyID);

}
