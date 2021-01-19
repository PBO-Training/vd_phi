package com.brycen.hrm.config.company;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.CompanyEntity;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long>{
    Optional<CompanyEntity> findByCompanyCode(String companyCode);
}
