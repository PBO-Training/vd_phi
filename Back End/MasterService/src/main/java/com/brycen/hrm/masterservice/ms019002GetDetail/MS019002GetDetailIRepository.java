package com.brycen.hrm.masterservice.ms019002GetDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.ScopeWorkEntity;

/**
 * [Description]: Get Details Repository for Role Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS019002GetDetailIRepository extends JpaRepository<ScopeWorkEntity, Long> {

    /**
     * [Description]: Find a role<br/>
     * [ Remarks ]:<br/>
     *
     * @param roleID
     * @param companyID
     * @param isDelete
     * @return Role Details
     */
    Optional<ScopeWorkEntity> findByScopeWorkIDAndCompanyIDAndIsDelete(long scopeWorkID, int companyID, boolean isDelete);
}
