package com.brycen.hrm.masterservice.ms018002Update;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.RoleEntity;

/**
 * [Description]: Update Repository for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS018002UpdateIRepository extends JpaRepository<RoleEntity, Long> {

    /**
     * [Description]: Find a role by role ID<br/>
     * [ Remarks ]:<br/>
     *
     * @param roleID
     * @param companyID
     * @param isDelete
     * @return a role details
     */
    Optional<RoleEntity> findByRoleIDAndCompanyIDAndIsDelete(long roleID, int companyID, boolean isDelete);

    /**
     * [Description]: Find a role to avoid duplicate <br/>
     * [ Remarks ]:<br/>
     *
     * @param roleCode
     * @param companyID
     * @param roleID
     * @return A role details
     */
    @Query(value = "SELECT * FROM role r WHERE r.role_code = :roleCode AND r.company_id = :companyID AND r.role_id <> :roleID", nativeQuery = true)
    RoleEntity findRoleByRoleCodeAndCompanyID(@Param("roleCode") String roleCode, @Param("companyID") int companyID, @Param("roleID") Long roleID);

}
