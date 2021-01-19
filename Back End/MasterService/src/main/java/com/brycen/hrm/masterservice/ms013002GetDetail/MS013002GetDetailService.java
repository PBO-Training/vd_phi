package com.brycen.hrm.masterservice.ms013002GetDetail;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Interface is called by controller to do actions find a holiday<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS013002GetDetailService {
    /**
     * [Description]: Method find a holiday with id specification<br/>
     * [ Remarks ]:<br/>
     *
     * @param id
     * @return MS013002UpdateResponse - Model contain data what need to send to client
     */
    BaseResponse getDetail(long holidayID, int companyID);
}
