package com.brycen.hrm.masterservice.ms023001Update;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.constant.ErrorValue;
import com.brycen.hrm.constant.UrlAPI;
import com.brycen.hrm.entity.ShiftWorkOptionEntity;
import com.brycen.hrm.logger.LogLevel;
import com.brycen.hrm.logger.LoggerService;

/**
 * [Description]: Delete Service Implementation for Scope Work Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Service
public class MS023001UpdateImpl implements MS023001UpdateIService {
	
	@Autowired
	public MS023001UpdateRepository updateRepository;
    /**
     * Call entity manager to support creation a native query statement
     */
    @Autowired
    public EntityManager em;

    /**
     * Call query to save an instance native query statement
     */
    private Query query;

    /**
     * Write log
     */
    @Autowired
    LoggerService logger;
    
    public ErrorResponse checkValue(ShiftWorkOptionEntity shiftworkoptionEntity,Optional<ShiftWorkOptionEntity> currentShiftWork, int companyID) {
    	ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        
        ShiftWorkOptionEntity shiftWorkOption = updateRepository.findByShiftWorkOptionCodeAndCompanyID(shiftworkoptionEntity.getShiftWorkOptionCode(), companyID,currentShiftWork.get().getShiftWorkOptionID());
		if(shiftWorkOption != null) {
			errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SHIFTWORK)
            .append(ErrorValue.API_UPDATE_DETAIL_SHIFTWORK).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
		    errorItemName.append("shiftWorkCode");
		    error.setCode(errorItemCode.toString());
		    error.setItemName(errorItemName.toString());
		    return error;
		}
        return null;
    	
    }
    
    @Transactional
	@Override
	public BaseResponse update(ShiftWorkOptionEntity shiftworkoptionEntity, int companyID) {
    	BaseResponse baseRes = new BaseResponse();
    	Optional<ShiftWorkOptionEntity> currentShiftWork = updateRepository.findByShiftWorkOptionIDAndCompanyIDAndIsDelete(shiftworkoptionEntity.getShiftWorkOptionID(), companyID, false);
    	ErrorResponse error = checkValue(shiftworkoptionEntity, currentShiftWork, companyID);
    	if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS023001_UPDATE_SHIFTWORKOPTION, shiftworkoptionEntity, baseRes, "");
            return baseRes;
        }
    	StringBuffer strSql = new StringBuffer();
    	strSql.append("UPDATE shift_work_option s ");
        strSql.append("SET s.is_delete = 0");
        if (!CheckValueService.checkNull(shiftworkoptionEntity.getShiftWorkOptionCode())) {
        	strSql.append(" , s.shift_work_option_code= :shiftWorkOptionCode");
        }
        if (!CheckValueService.checkNull(shiftworkoptionEntity.getShiftWorkOptionName())) {
        	strSql.append(" , s.shift_work_option_name= :shiftWorkOptionName");
        }
        	strSql.append(" , s.shift_work_option_description= :shiftWorkOptionDescriptions");
        if (!CheckValueService.checkNull(shiftworkoptionEntity.getShiftWorkOptionStartTimeAM())) {
        	strSql.append(" , s.shift_work_option_start_time_am= :shiftWorkOptionStartTimeAM");
        }
        if (!CheckValueService.checkNull(shiftworkoptionEntity.getShiftWorkOptionStartTimePM())) {
        	strSql.append(" , s.shift_work_option_start_time_pm= :shiftWorkOptionStartTimePM");
        }
        if (!CheckValueService.checkNull(shiftworkoptionEntity.getShiftWorkOptionEndTimeAM())) {
        	strSql.append(" , s.shift_work_option_end_time_am= :shiftWorkOptionEndTimeAM");
        }
        if (!CheckValueService.checkNull(shiftworkoptionEntity.getShiftWorkOptionEndTimePM())) {
        	strSql.append(" , s.shift_work_option_end_time_pm= :shiftWorkOptionEndTimePM");
        }
        strSql.append(" WHERE s.shift_work_option_id = :shiftWorkOptionID");
        strSql.append(" AND s.company_id = :companyID ");
        
        query = em.createNativeQuery(strSql.toString());
        query.setParameter("shiftWorkOptionID",shiftworkoptionEntity.getShiftWorkOptionID() );
        query.setParameter("companyID", companyID);
        query.setParameter("shiftWorkOptionDescriptions",shiftworkoptionEntity.getShiftWorkOptionDescription());
        if (!CheckValueService.checkNull(shiftworkoptionEntity.getShiftWorkOptionCode())) {
        	query.setParameter("shiftWorkOptionCode",shiftworkoptionEntity.getShiftWorkOptionCode());
        }
        if (!CheckValueService.checkNull(shiftworkoptionEntity.getShiftWorkOptionName())) {
        	query.setParameter("shiftWorkOptionName",shiftworkoptionEntity.getShiftWorkOptionName());
        }              	      
        if (!CheckValueService.checkNull(shiftworkoptionEntity.getShiftWorkOptionStartTimeAM())) {
        	query.setParameter("shiftWorkOptionStartTimeAM",shiftworkoptionEntity.getShiftWorkOptionStartTimeAM());
        }
        if (!CheckValueService.checkNull(shiftworkoptionEntity.getShiftWorkOptionStartTimePM())) {
        	query.setParameter("shiftWorkOptionStartTimePM", shiftworkoptionEntity.getShiftWorkOptionStartTimePM());
        }
        if (!CheckValueService.checkNull(shiftworkoptionEntity.getShiftWorkOptionEndTimeAM())) {
        	query.setParameter("shiftWorkOptionEndTimeAM", shiftworkoptionEntity.getShiftWorkOptionEndTimeAM());
        }
        if (!CheckValueService.checkNull(shiftworkoptionEntity.getShiftWorkOptionEndTimePM())) {
        	query.setParameter("shiftWorkOptionEndTimePM", shiftworkoptionEntity.getShiftWorkOptionEndTimePM());
        }
        baseRes.setContent(query.executeUpdate());
		return baseRes;
	}

}
