package com.brycen.hrm.masterservice.ms001002Init;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.EmployeeEntity;

/**
 * [Description]: Initialize Repository for details screen of User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS001002InitIRepository extends JpaRepository<EmployeeEntity, Long> {

}
