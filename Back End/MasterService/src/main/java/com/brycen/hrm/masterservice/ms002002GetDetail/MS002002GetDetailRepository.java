package com.brycen.hrm.masterservice.ms002002GetDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.DepartmentEntity;

/**
 * [Description]: Interface update repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS002002GetDetailRepository extends JpaRepository<DepartmentEntity, Long> {
    /**
     * [Description]: Method find department by companyID and departmentID<br/>
     * [ Remarks ]:<br/>
     *
     * @param departmentID
     * @param companyID
     * @return Department or error with errorName and errorCode
     */
    Optional<DepartmentEntity> findByDepartmentIDAndCompanyIDAndIsDelete(long departmentID, int companyID, boolean isDelete);
}
