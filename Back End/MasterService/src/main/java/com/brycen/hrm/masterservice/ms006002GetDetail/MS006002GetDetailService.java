package com.brycen.hrm.masterservice.ms006002GetDetail;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Interface is called by controller to do actions find a level language<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS006002GetDetailService {
    /**
     * [Description]: Method find a level language with id specification<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return MS006002GetDetailResponse - Model contain data what need to send to client
     */
    BaseResponse getDetail(long levelLanguageID, int companyID);
}
