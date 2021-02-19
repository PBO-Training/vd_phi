package com.brycen.hrm.masterservice.ms023001Create;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brycen.hrm.common.base.BaseResponse;
import com.brycen.hrm.common.base.ErrorResponse;
import com.brycen.hrm.common.checkValue.CheckValueService;
import com.brycen.hrm.config.auditor.AuditorAwareImpl;
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
public class MS023001CreateImpl implements MS023001CreateIService {

	@Autowired
	public MS023001CreateRepository createRepository;
	
    @Autowired
    public EntityManager em;
 
    private Query query;

    @Autowired
    LoggerService logger;
    
    public ErrorResponse checkValue(ShiftWorkOptionEntity shiftworkoptionEntity, int companyID) {
    	ErrorResponse error = new ErrorResponse();
        StringBuffer errorItemName = new StringBuffer();
        StringBuffer errorItemCode = new StringBuffer();
        
        ShiftWorkOptionEntity shiftWorkOption = createRepository.findByShiftWorkOptionCodeAndCompanyID(shiftworkoptionEntity.getShiftWorkOptionCode(), companyID);
		if(shiftWorkOption != null) {
			errorItemCode.append(ErrorValue.TYPE_INPUT_VALUE_ERROR).append(ErrorValue.SERVICE_API_MASTER).append(ErrorValue.PACKAGE_SHIFTWORK)
            .append(ErrorValue.API_CREATE_DETAIL_SHIFTWORK).append(ErrorValue.METHOD_POST).append(ErrorValue.REASON_VALUE_ILLEGAL);
		    errorItemName.append("shiftWorkCode");
		    error.setCode(errorItemCode.toString());
		    error.setItemName(errorItemName.toString());
		    return error;
		}
        return null;
    	
    }
    
    @Transactional
	@Override
	public BaseResponse insert(ShiftWorkOptionEntity shiftworkoptionEntity, int companyID) {
    	BaseResponse baseRes = new BaseResponse();
    	ErrorResponse error = checkValue(shiftworkoptionEntity, companyID);
    	if (error != null) {
            baseRes.setError(error);
            logger.write(LogLevel.ERROR, UrlAPI.MS023001_CREATE_SHIFTWORKOPTION, shiftworkoptionEntity, baseRes, "");
            return baseRes;
        }
    	StringBuffer strSql = new StringBuffer();
    	AuditorAwareImpl auditorAwareImpl = new AuditorAwareImpl();
    	strSql.append("INSERT INTO shift_work_option (company_id,create_by,create_date,is_delete,update_by,update_date, ");
    	strSql.append(" shift_work_option_code, shift_work_option_name,shift_work_option_description,shift_work_option_start_time_am, ");
    	strSql.append(" shift_work_option_start_time_pm,shift_work_option_end_time_am,shift_work_option_end_time_pm )");
    	strSql.append("VALUES ( :companyID, :createBy, now(),0,:updateBy, now()");
        strSql.append(" , :shiftWorkOptionCode");
        strSql.append(" , :shiftWorkOptionName");      
        strSql.append(" , :shiftWorkOptionDescriptions");        
        strSql.append(" , :shiftWorkOptionStartTimeAM");                
        strSql.append(" , :shiftWorkOptionStartTimePM");                
        strSql.append(" , :shiftWorkOptionEndTimeAM");        
        strSql.append(" , :shiftWorkOptionEndTimePM )");
        
        query = em.createNativeQuery(strSql.toString());
        query.setParameter("companyID", companyID);
        query.setParameter("createBy",auditorAwareImpl.getCurrentAuditor().get());
        query.setParameter("updateBy", auditorAwareImpl.getCurrentAuditor().get());
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
