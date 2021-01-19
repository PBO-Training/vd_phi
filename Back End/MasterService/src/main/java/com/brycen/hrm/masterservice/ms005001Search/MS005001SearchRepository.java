package com.brycen.hrm.masterservice.ms005001Search;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.LanguageEntity;

/**
 * [Description]: Interface search repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS005001SearchRepository extends JpaRepository<LanguageEntity, Long> {

}
