package com.brycen.hrm.masterservice.ms001001Search;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.ContentResponse;

/**
 * [Description]: Service for User Master Tables<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS001001SearchIService {

    /**
     * [Description]: Search user with multiple parameter request<br/>
     * [ Remarks ]:<br/>
     *
     * @param ms001001SearchRequest
     * @param companyID
     * @return content and error status
     */
    ContentResponse searchUser(MS001001SearchRequest ms001001SearchRequest, int companyID);

}
