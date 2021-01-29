package com.brycen.hrm.masterservice.ms023001GetDetail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.ShiftWorkOptionEntity;
@Service
public class MS023001GetDetailImpl implements MS023001GetDetailService {
	
	@Autowired
	MS023001GetDetailRepository getDetailRepository;

	@Override
	public BaseResponse getDetail(long shiftWorkOptionID, int companyID) {
		BaseResponse baseRes = new BaseResponse();
		Optional<ShiftWorkOptionEntity>  shifworkoption = getDetailRepository.findByShiftWorkOptionIDAndCompanyIDAndIsDelete(shiftWorkOptionID, companyID, false);
		ShiftWorkOptionEntity ent = shifworkoption.get();
		MS023001GetDetailResponse resp = new MS023001GetDetailResponse(ent);
		baseRes.setContent(resp);
		return baseRes;
	}

}
