package com.brycen.hrm.masterservice.ms002002Update;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
public interface MS002002UpdateRepository extends JpaRepository<DepartmentEntity, Long> {
    /**
     * [Description]: Method find department by companyID and departmentID<br/>
     * [ Remarks ]:<br/>
     *
     * @param departmentID
     * @param companyID
     * @param isDelete
     * @return Department or error with errorName and errorCode
     */
    Optional<DepartmentEntity> findByDepartmentIDAndCompanyIDAndIsDelete(long departmentID, int companyID, boolean isDelete);

    /**
     * [Description]: Find a department details<br/>
     * [ Remarks ]:<br/>
     *
     * @param departmentCode
     * @param companyID
     * @param departmentID
     * @return a department details
     */
    @Query(value = "SELECT * FROM department d WHERE d.department_code = :departmentCode AND d.company_id = :companyID AND d.department_id <> :departmentID", nativeQuery = true)
    DepartmentEntity findDepartmentByDepartmentCodeAndCompanyID(@Param("departmentCode") String departmentCode, @Param("companyID") int companyID, @Param("departmentID")Long departmentID);

}
