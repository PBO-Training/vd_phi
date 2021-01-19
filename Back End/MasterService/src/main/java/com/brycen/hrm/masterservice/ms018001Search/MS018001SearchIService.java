package com.brycen.hrm.masterservice.ms018001Search;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Search Service for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS018001SearchIService {

    /**
     * [Description]: Search a list role<br/>
     * [ Remarks ]:<br/>
     *
     * @param searchRequest
     * @param companyID
     * @return List role
     */
    BaseResponse search(MS018001SearchRequest searchRequest, int companyID);

}
