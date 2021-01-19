package com.brycen.hrm.masterservice.ms019002Update;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.ScopeWorkEntity;

/**
 * [Description]: Update Repository for Scope Work Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS019002UpdateIRepository extends JpaRepository<ScopeWorkEntity, Long> {

    /**
     * [Description]: Find a scopeWork by scopeWork ID<br/>
     * [ Remarks ]:<br/>
     *
     * @param scopeWorkID
     * @param companyID
     * @param isDelete
     * @return a scopeWork details
     */
    Optional<ScopeWorkEntity> findByScopeWorkIDAndCompanyIDAndIsDelete(long scopeWorkID, int companyID, boolean isDelete);

    /**
     * [Description]: Find a scopeWork to avoid duplicate <br/>
     * [ Remarks ]:<br/>
     *
     * @param scopeWorkCode
     * @param companyID
     * @param scopeWorkID
     * @return A scopeWork details
     */
    @Query(value = "SELECT * FROM scope_work s WHERE s.scope_work_code = :scopeWorkCode AND s.company_id = :companyID AND s.scope_work_id <> :scopeWorkID", nativeQuery = true)
    ScopeWorkEntity findScopeWorkByScopeWorkCodeAndCompanyID(@Param("scopeWorkCode") String scopeWorkCode, @Param("companyID") int companyID, @Param("scopeWorkID") Long scopeWorkID);

}
