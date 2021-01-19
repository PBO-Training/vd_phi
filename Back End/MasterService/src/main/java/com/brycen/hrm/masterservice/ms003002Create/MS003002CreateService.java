package com.brycen.hrm.masterservice.ms003002Create;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Interface is called by controller to do actions create<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS003002CreateService {
	/**
	 * [Description]: Method create new skill<br/>
	 * [ Remarks ]:<br/>
	 *
	 * @param skillEntity
	 */
	BaseResponse save(MS003002CreateRequest request, int companyID);
}
