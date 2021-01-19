package com.brycen.hrm.masterservice.ms999001Init;

import com.brycen.hrm.common.base.BaseResponse;

public interface MS999001InitService {
    /**
     * [Description]:Method get statistic employee includes total employee, employee in a department<br/>
     * [ Remarks ]:<br/>
     *
     * @param companyID
     * @return BaseResponse - Contain data have constructure to send to client
     */
    BaseResponse getTotalEmployee(int companyID);
}
