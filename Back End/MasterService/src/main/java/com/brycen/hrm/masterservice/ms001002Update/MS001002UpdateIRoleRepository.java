package com.brycen.hrm.masterservice.ms001002Update;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.RoleEntity;

/**
 * [Description]:<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS001002UpdateIRoleRepository extends JpaRepository<RoleEntity, Long> {

    /**
     * [Description]: Find a role by roleID<br/>
     * [ Remarks ]:<br/>
     *
     * @param roleID
     * @param companyID
     * @param isDelete
     * @return A role details
     */
    Optional<RoleEntity> findByRoleIDAndCompanyIDAndIsDelete(long roleID, int companyID, boolean isDelete);
}
