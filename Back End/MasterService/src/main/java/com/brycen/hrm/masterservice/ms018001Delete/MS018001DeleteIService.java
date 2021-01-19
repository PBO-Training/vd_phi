package com.brycen.hrm.masterservice.ms018001Delete;

import java.util.List;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Delete Service for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS018001DeleteIService {

    /**
     * [Description]: Delete a list role<br/>
     * [ Remarks ]:<br/>
     *
     * @param listDelete
     * @param companyID
     * @return Content and error status
     */
    BaseResponse delete(List<Long> listDelete, int companyID);

}
