package com.brycen.hrm.masterservice.ms008001Search;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.StatusEmployeeEntity;

/**
 * [Description]: Search Repository for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS008001SearchIRepository extends JpaRepository<StatusEmployeeEntity, Long> {

}
