package com.brycen.hrm.masterservice.ms019001Search;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Search Service for Scope Work Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS019001SearchIService {

    /**
     * [Description]: Search a list scope work<br/>
     * [ Remarks ]:<br/>
     *
     * @param searchRequest
     * @param companyID
     * @return List scope work
     */
    BaseResponse search(MS019001SearchRequest searchRequest, int companyID);

}
