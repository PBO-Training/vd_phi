package com.brycen.hrm.masterservice.ms006002Create;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.LevelLanguageEntity;

/**
 * [Description]: Interface is called by controller to do actions create<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS006002CreateService {
    /**
     * [Description]: Method create new level language<br/>
     * [ Remarks ]:<br/>
     *
     * @param levelLanguageEntity
     */
    BaseResponse save(LevelLanguageEntity levelLanguageEntity, int companyID);
}
