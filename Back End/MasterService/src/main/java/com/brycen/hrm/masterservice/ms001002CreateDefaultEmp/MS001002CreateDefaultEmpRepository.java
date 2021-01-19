package com.brycen.hrm.masterservice.ms001002CreateDefaultEmp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.EmployeeEntity;

/**
 * [Description]: Interface create repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS001002CreateDefaultEmpRepository extends JpaRepository<EmployeeEntity, Long>{
    /**
     * [Description]: Method find Employee by companyID and employeeCode<br/>
     * [ Remarks ]:<br/>
     *
     * @param companyID
     * @param employeeCode
     * @return Optional of EmployeeEntity
     */
    @Query(value = "SELECT * FROM employee e WHERE e.employee_code =:employeeCode AND e.company_id =:companyID AND e.is_delete=:isDelete ", nativeQuery = true)
    List<EmployeeEntity> findEmployeeByEmployeeCodeAndCompanyIDAndIsDelete(@Param("employeeCode") String employeeCode, @Param("companyID") int companyID,
            @Param("isDelete") boolean isDelete);
    
    @Query(value = "SELECT company_code FROM  company c WHERE c.company_id =:companyID AND c.is_delete=:isDelete ", nativeQuery = true)
    String findCompanyCodeByCompanyIDAndIsDelete(@Param("companyID") int companyID, @Param("isDelete") boolean isDelete);
    
    @Query(value = "SELECT DISTINCT employee_id FROM employee e WHERE e.company_id =:companyID AND e.is_delete=:isDelete ", nativeQuery = true)
    List<Long> findAllEmployeeID(@Param("companyID") int companyID, @Param("isDelete") boolean isDelete);
    
    @Query(value = "SELECT DISTINCT employee_id FROM user u WHERE u.company_id =:companyID AND u.is_delete=:isDelete ", nativeQuery = true)
    List<Long> findAllEmployeeIDAtUser(@Param("companyID") int companyID, @Param("isDelete") boolean isDelete);
}
