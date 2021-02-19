package com.brycen.hrm.masterservice.ms023001Create;

import org.springframework.stereotype.Service;
import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.entity.ShiftWorkOptionEntity;

/**
 * [Description]: Delete Service for Scope Work Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public interface MS023001CreateIService {

    /**
     * [Description]: Delete a list scope work<br/>
     * [ Remarks ]:<br/>
     *
     * @param listDelete
     * @param companyID
     * @return Content and error status
     */
    BaseResponse insert(ShiftWorkOptionEntity shiftworkoptionEntity, int companyID);

}
