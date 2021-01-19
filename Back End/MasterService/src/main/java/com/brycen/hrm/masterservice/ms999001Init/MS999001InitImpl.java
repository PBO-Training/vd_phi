package com.brycen.hrm.masterservice.ms999001Init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

@Service
public class MS999001InitImpl implements MS999001InitService {

    /**
     * Use take result statistic for dashboard
     */
    @Autowired
    MS999001InitProcess initDashboardProcess;

    @Override
    public BaseResponse getTotalEmployee(int companyID) {
        BaseResponse baseRes = new BaseResponse();
        baseRes = initDashboardProcess.initStatisticTotalEmployee(companyID);
        return baseRes;
    }

}
