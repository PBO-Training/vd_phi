package com.brycen.hrm.masterservice.ms002002Create;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.DepartmentEntity;

/**
 * [Description]: Interface create repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS002002CreateRepository extends JpaRepository<DepartmentEntity, Long> {
    /**
     * [Description]: Find a department with departmentCode<br/>
     * [ Remarks ]:<br/>
     *
     * @param departmentCode
     * @param companyID
     * @return a details department
     */
    @Query(value = "SELECT * FROM department d WHERE d.department_code = :departmentCode AND d.company_id = :companyID", nativeQuery = true)
    DepartmentEntity findDepartmentByDepartmentCodeAndCompanyID(@Param("departmentCode") String departmentCode, @Param("companyID") int companyID);

}
