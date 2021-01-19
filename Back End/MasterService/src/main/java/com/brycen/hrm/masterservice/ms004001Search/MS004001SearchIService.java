package com.brycen.hrm.masterservice.ms004001Search;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Search Service for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS004001SearchIService {

    /**
     * [Description]: Search skill level with multiple parameter request<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms004001SearchRequest
     * @param companyID
     * @return Search result
     */
    BaseResponse search(MS004001SearchRequest ms004001SearchRequest, int companyID);

}
