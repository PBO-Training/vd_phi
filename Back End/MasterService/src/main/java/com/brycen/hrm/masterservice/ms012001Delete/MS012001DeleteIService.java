package com.brycen.hrm.masterservice.ms012001Delete;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * [Description]: Interface is called by controller to do actions delete a vacation type<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS012001DeleteIService {
    /**
     * [Description]: Method find vacation type with id specification and delete it<br/>
     * [ Remarks ]:<br/>
     *
     * @param listvacationTypeID
     * @param companyID
     * @return BaseResponse - Model contain data what need to send to client
     */
    int delete(List<Long> vacationTypeDelete, int companyID);
}
