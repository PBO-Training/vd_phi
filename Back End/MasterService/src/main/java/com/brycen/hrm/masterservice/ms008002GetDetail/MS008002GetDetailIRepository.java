package com.brycen.hrm.masterservice.ms008002GetDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.StatusEmployeeEntity;

/**
 * [Description]: Get Details Repository for Employee Status Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS008002GetDetailIRepository extends JpaRepository<StatusEmployeeEntity, Long> {

    /**
     * [Description]: Find a employee status with statusEmployeeID, companyID and delete status<br/>
     * [ Remarks ]:<br/>
     *
     * @param statusEmployeeID
     * @param companyID
     * @param isDelete
     * @return A details employee status
     */
    Optional<StatusEmployeeEntity> findByStatusEmployeeIDAndCompanyIDAndIsDelete(long statusEmployeeID, int companyID, boolean isDelete);

}
