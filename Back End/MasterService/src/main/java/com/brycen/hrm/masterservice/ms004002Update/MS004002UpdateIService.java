package com.brycen.hrm.masterservice.ms004002Update;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.LevelSkillEntity;

/**
 * [Description]: Update Service for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS004002UpdateIService {

    /**
     * [Description]: Update a new skill level<br/>
     * [ Remarks ]:<br/>
     *
     * @param levelSkillEntity
     * @param companyID
     * @return Content and error status
     */
    BaseResponse save(LevelSkillEntity levelSkillEntity, int companyID);

}
