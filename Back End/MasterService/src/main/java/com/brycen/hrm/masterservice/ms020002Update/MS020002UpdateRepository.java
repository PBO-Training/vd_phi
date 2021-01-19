package com.brycen.hrm.masterservice.ms020002Update;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.DegreeEntity;

/**
 * [Description]: Interface update repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS020002UpdateRepository extends JpaRepository<DegreeEntity, Long> {
    /**
     * [Description]: Method find degree by companyID and degreeID<br/>
     * [ Remarks ]:<br/>
     *
     * @param degreeID
     * @param companyID
     * @param isDelete
     * @return degree or error with errorName and errorCode
     */
    Optional<DegreeEntity> findByDegreeIDAndCompanyIDAndIsDelete(long degreeID, int companyID, boolean isDelete);

    /**
     * [Description]: Find a degree details<br/>
     * [ Remarks ]:<br/>
     *
     * @param degreeCode
     * @param companyID
     * @param degreeID
     * @return a degree details
     */
    @Query(value = "SELECT * FROM degree d WHERE d.degree_code = :degreeCode AND d.company_id = :companyID AND d.degree_id <> :degreeID", nativeQuery = true)
    DegreeEntity findDegreeByDegreeCodeAndCompanyID(@Param("degreeCode") String degreeCode, @Param("companyID") int companyID,
            @Param("degreeID") Long degreeID);

}
