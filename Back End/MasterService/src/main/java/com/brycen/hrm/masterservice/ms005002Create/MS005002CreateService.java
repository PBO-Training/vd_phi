package com.brycen.hrm.masterservice.ms005002Create;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.LanguageEntity;

/**
 * [Description]: Interface is called by controller to do actions create<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS005002CreateService {
	BaseResponse save(LanguageEntity languageEntity, int companyID);
}
