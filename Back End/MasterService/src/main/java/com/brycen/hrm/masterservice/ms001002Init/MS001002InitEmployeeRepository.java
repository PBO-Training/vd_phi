package com.brycen.hrm.masterservice.ms001002Init;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brycen.hrm.entity.EmployeeEntity;

/**
 * [Description]: Init Repository for user<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public interface MS001002InitEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
	List<EmployeeEntity> findAllByCompanyIDAndIsDelete(int companyID, boolean isDelete);

}
