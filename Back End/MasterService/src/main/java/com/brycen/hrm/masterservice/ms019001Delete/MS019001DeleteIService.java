package com.brycen.hrm.masterservice.ms019001Delete;

import java.util.List;

import org.springframework.stereotype.Service;
import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Delete Service for Scope Work Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS019001DeleteIService {

    /**
     * [Description]: Delete a list scope work<br/>
     * [ Remarks ]:<br/>
     *
     * @param listDelete
     * @param companyID
     * @return Content and error status
     */
    BaseResponse delete(List<Long> listDelete, int companyID);

}
