package com.brycen.hrm.masterservice.ms006001Delete;

import java.util.List;

/**
 * [Description]: Interface is called by controller to do actions delete a language level<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS006001DeleteService {
    /**
     * [Description]: Method find level language with id specification and delete it<br/>
     * [ Remarks ]:<br/>
     *
     * @param listLanguageLevelID
     * @param companyID
     * @return BaseResponse - Model contain data what need to send to client
     */
    int delete(List<Long> listLanguageLevelID, int companyID);
}
