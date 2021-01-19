package com.brycen.hrm.masterservice.ms001002Create;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.RoleEntity;

/**
 * [Description]: Role Repository<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS001002CreateIRoleRepository extends JpaRepository<RoleEntity, Long> {

    /**
     * [Description]: Find role to corresponds roleID and companyID and isDelete<br/>
     * [ Remarks ]:<br/>
     *
     * @param roleID
     * @param companyID
     * @param isDelete
     * @return Role to corresponds roleID
     */
    Optional<RoleEntity> findByRoleIDAndCompanyIDAndIsDelete(Long roleID, int companyID, boolean isDelete);

}
