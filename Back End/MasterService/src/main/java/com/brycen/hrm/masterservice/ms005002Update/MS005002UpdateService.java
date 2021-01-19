package com.brycen.hrm.masterservice.ms005002Update;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.LanguageEntity;

/**
 * [Description]: Interface is called by controller to do actions update and find a language<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS005002UpdateService {
	/**
	 * [Description]: Save language after change data in language<br/>
	 * [ Remarks ]:<br/>
	 *
	 * @param languageEntity
	 */
	BaseResponse save(LanguageEntity languageEntity, int companyID); 
}
