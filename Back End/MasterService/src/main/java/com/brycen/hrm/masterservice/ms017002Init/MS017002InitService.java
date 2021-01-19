package com.brycen.hrm.masterservice.ms017002Init;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]:Interface Init Service for Role-Screen Detail<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS017002InitService {

    /**
     * [Description]:Initialize data for Role-Screen Detail<br/>
     * [ Remarks ]:<br/>
     *
     * @param compannyID
     * @return BaseResponse
     */
    BaseResponse init(int compannyID);
}
