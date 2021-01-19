package com.brycen.hrm.masterservice.ms004001Delete;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * [Description]: Delete Service for Skill Level Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS004001DeleteIService {

    /**
     * [Description]: Delete list skill level in skillLevelDelete with company code is companyID<br/>
     * [ Remarks ]:<br/>
     *
     * @param skillLevelDelete
     * @param companyID
     * @return 1 if success and 0 if fail
     */
    int delete(List<Long> skillLevelDelete, int companyID);

}
