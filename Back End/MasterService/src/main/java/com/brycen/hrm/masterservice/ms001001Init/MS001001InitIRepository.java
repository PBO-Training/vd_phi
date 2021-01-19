package com.brycen.hrm.masterservice.ms001001Init;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.RoleEntity;

/**
 * [Description]: Init Repository for User Master Tabel<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS001001InitIRepository extends JpaRepository<RoleEntity, Long> {

}
