package com.brycen.hrm.masterservice.ms009002GetDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.CustomerEntity;

/**
 * [Description]: Interface update repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS009002GetDetailRepository extends JpaRepository<CustomerEntity, Long> {
    /**
     * [Description]: Method find customer by companyID and customerID<br/>
     * [ Remarks ]:<br/>
     *
     * @param customerID
     * @param companyID
     * @return Customer or error with errorName and errorCode
     */
    Optional<CustomerEntity> findByCustomerIDAndCompanyIDAndIsDelete(long customerID, int companyID, boolean isDelete);
}
