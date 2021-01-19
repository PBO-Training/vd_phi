package com.brycen.hrm.masterservice.ms009002Create;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.CustomerEntity;

/**
 * [Description]: Interface create repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS009002CreateRepository extends JpaRepository<CustomerEntity, Long> {
    /**
     * [Description]: Find a customer<br/>
     * [ Remarks ]:<br/>
     *
     * @param customerCode
     * @param companyID
     * @return A customer details
     */
    @Query(value = "SELECT * FROM customer c WHERE c.customer_code = :customerCode AND c.company_id = :companyID", nativeQuery = true)
    CustomerEntity findCustomerByCustomerCodeAndCompanyID(@Param("customerCode") String customerCode, @Param("companyID") int companyID);
}
