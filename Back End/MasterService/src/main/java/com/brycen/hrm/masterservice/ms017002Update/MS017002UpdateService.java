package com.brycen.hrm.masterservice.ms017002Update;

import java.util.List;

import com.brycen.hrm.common.base.BaseResponse;

public interface MS017002UpdateService {

    /**
     * [Description]:Method update Role-Screen Detail<br/>
     * [ Remarks ]:<br/>
     *
     * @param listMs017002UpdateRequest
     * @param companyID
     * @return
     */
    BaseResponse update(List<MS017002UpdateRequest> listMs017002UpdateRequest, int companyID);
}
