package com.brycen.hrm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.RoleEntity;

/**
 * [Description]: Employee Repository<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface RoleEntityIRepository extends JpaRepository<RoleEntity, Long> {
    
    /**
     * [Description]: Find role to corresponds roleCode<br/>
     * [ Remarks ]:<br/>
     *
     * @param roleCode
     * @param companyID
     * @return Role to corresponds roleCode
     */
    List<RoleEntity> findByRoleCodeAndCompanyID(String roleCode, int companyID);

    /**
     * [Description]: Find role to corresponds roleID and companyID<br/>
     * [ Remarks ]:<br/>
     *
     * @param role
     * @param companyID
     * @return Role to corresponds roleID
     */
    Optional<RoleEntity> findByRoleIDAndCompanyID(long roleID, int companyID);

    /**
     * [Description]: Find list role by companyID and isDelete<br/>
     * [ Remarks ]:<br/>
     *
     * @param companyID
     * @param isDelete
     * @return list role
     */
    List<RoleEntity> findAllByCompanyIDAndIsDelete(int companyID, boolean isDelete);
    
    /**
     * [Description]:Find list role by roleCode, companyID and isDelete<br/>
     * [ Remarks ]:<br/>
     *
     * @param roleCode
     * @param companyID
     * @param isDelete
     * @return list role
     */
    List<RoleEntity> findByRoleCodeAndCompanyIDAndIsDelete(String roleCode, int companyID, boolean isDelete);
}
