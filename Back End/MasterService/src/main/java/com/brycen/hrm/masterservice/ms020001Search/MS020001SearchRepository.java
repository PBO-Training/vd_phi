package com.brycen.hrm.masterservice.ms020001Search;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.DegreeEntity;

/**
 * [Description]: Interface search repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS020001SearchRepository extends JpaRepository<DegreeEntity, Long> {

    /**
     * [Description]: Find list Degree by companyID and isDelete<br/>
     * [ Remarks ]:<br/>
     *
     * @param companyID
     * @param isDelete
     * @return list Degree
     */
    List<DegreeEntity> findAllByCompanyIDAndIsDelete(int companyID, boolean isDelete);
}
