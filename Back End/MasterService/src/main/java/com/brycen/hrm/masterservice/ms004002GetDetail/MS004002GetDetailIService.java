package com.brycen.hrm.masterservice.ms004002GetDetail;

import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;

/**
 * [Description]: Get Details Service for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS004002GetDetailIService {

    /**
     * [Description]: Get details a skill level<br/>
     * [ Remarks ]:<br/>
     *
     * @param levelSkillID
     * @param companyID
     * @return Skill level details
     */
    BaseResponse getDetail(long levelSkillID, int companyID);

}
