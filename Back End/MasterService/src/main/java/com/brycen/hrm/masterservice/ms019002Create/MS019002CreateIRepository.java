package com.brycen.hrm.masterservice.ms019002Create;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.ScopeWorkEntity;

/**
 * [Description]: Create Repository for Scope Work Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS019002CreateIRepository extends JpaRepository<ScopeWorkEntity, Long> {

    /**
     * [Description]: Find a scopeWork by code<br/>
     * [ Remarks ]:<br/>
     *
     * @param roleCode
     * @param companyID
     * @return A scopeWork details
     */
    @Query(value = "SELECT * FROM scope_work s WHERE s.scope_work_code = :scopeWorkCode AND s.company_id = :companyID", nativeQuery = true)
    ScopeWorkEntity findScopeWorkByScopeWorkCodeAndCompanyID(@Param("scopeWorkCode") String scopeWorkCode, @Param("companyID") int companyID);

}
