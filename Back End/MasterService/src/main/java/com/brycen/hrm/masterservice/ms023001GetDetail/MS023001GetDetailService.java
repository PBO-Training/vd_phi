package com.brycen.hrm.masterservice.ms023001GetDetail;

import com.brycen.hrm.common.base.BaseResponse;

public interface MS023001GetDetailService {
	BaseResponse getDetail(long shiftWorkOptionID, int companyID);
}	
