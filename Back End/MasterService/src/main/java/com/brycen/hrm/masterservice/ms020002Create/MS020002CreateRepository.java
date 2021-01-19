package com.brycen.hrm.masterservice.ms020002Create;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.DegreeEntity;

/**
 * [Description]: Interface create repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS020002CreateRepository extends JpaRepository<DegreeEntity, Long> {
    /**
     * [Description]: Find a Degree with degreeCode<br/>
     * [ Remarks ]:<br/>
     *
     * @param degreeCode
     * @param companyID
     * @return a details Degree
     */
    @Query(value = "SELECT * FROM degree d WHERE d.degree_code = :degreeCode AND d.company_id = :companyID", nativeQuery = true)
    DegreeEntity findDegreeByDegreeCodeAndCompanyID(@Param("degreeCode") String degreeCode, @Param("companyID") int companyID);

}
