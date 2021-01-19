package com.brycen.hrm.masterservice.ms005002GetDetail;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Interface is called by controller to do actions find a language<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS005002GetDetailService {
    /**
     * [Description]: Method find a language with id specification<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return MS005002GetDetailResponse - Model contain data what need to send to client
     */
    BaseResponse getDetail(long languageID, int companyID);
}
