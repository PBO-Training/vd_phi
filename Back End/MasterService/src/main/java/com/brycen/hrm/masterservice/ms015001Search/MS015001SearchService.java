package com.brycen.hrm.masterservice.ms015001Search;

import com.brycen.hrm.common.base.BaseResponse;

public interface MS015001SearchService {
    /**
     * [Description]: Use jpa to connect database and search contract type with params<br/>
     * [ Remarks ]:<br/>
     *
     * @param companyID - A number is set in header request
     */
    BaseResponse search(int companyID);
}
