package com.brycen.hrm.masterservice.ms008001Search;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Search Service for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS008001SearchIService {

    /**
     * [Description]: Search employee status with multiple parameter request<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms008001SearchRequest
     * @param companyID
     * @return content and error status
     */
    BaseResponse search(MS008001SearchRequest ms008001SearchRequest, int companyID);

}
