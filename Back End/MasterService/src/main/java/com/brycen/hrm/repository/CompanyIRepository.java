package com.brycen.hrm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.CompanyEntity;

@Repository
public interface CompanyIRepository extends JpaRepository<CompanyEntity, Integer>{
    /**
     * [Description]:Find Company by CompanyID<br/>
     * [ Remarks ]:<br/>
     *
     * @param companyID
     * @return
     */
    Optional<CompanyEntity> findByCompanyID(int companyID);
    
    /**
     * [Description]:Find Company by CompanyCode<br/>
     * [ Remarks ]:<br/>
     *
     * @param companyCode
     * @return
     */
    Optional<CompanyEntity> findByCompanyCode(String companyCode);
}