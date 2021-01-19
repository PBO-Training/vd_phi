package com.brycen.hrm.masterservice.ms018002Create;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.RoleEntity;

/**
 * [Description]: Create Repository for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS018002CreateIRepository extends JpaRepository<RoleEntity, Long> {

    /**
     * [Description]: Find a role by code<br/>
     * [ Remarks ]:<br/>
     *
     * @param roleCode
     * @param companyID
     * @return A role details
     */
    @Query(value = "SELECT * FROM role r WHERE r.role_code = :roleCode AND r.company_id = :companyID", nativeQuery = true)
    RoleEntity findRoleByRoleCodeAndCompanyID(@Param("roleCode") String roleCode, @Param("companyID") int companyID);

}
