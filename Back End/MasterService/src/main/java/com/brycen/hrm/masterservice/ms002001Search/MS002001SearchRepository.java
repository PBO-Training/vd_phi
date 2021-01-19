package com.brycen.hrm.masterservice.ms002001Search;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.DepartmentEntity;

/**
 * [Description]: Interface search repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS002001SearchRepository extends JpaRepository<DepartmentEntity, Long> {

    /**
     * [Description]: Find list department by companyID and isDelete<br/>
     * [ Remarks ]:<br/>
     *
     * @param companyID
     * @param isDelete
     * @return list department
     */
    List<DepartmentEntity> findAllByCompanyIDAndIsDelete(int companyID, boolean isDelete);
}
