package com.brycen.hrm.masterservice.ms001001Search;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.UserEntity;

/**
 * [Description]: Search Repository for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS001001SearchIRepository extends JpaRepository<UserEntity, Long> {

}
