package com.brycen.hrm.masterservice.ms009002Update;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
public interface MS009002UpdateRepository extends JpaRepository<CustomerEntity, Long> {
    /**
     * [Description]: Method find customer by companyID and customerID<br/>
     * [ Remarks ]:<br/>
     *
     * @param customerID
     * @param companyID
     * @param isDelete
     * @return customer or error with errorName and errorCode
     */
    Optional<CustomerEntity> findByCustomerIDAndCompanyIDAndIsDelete(long customerID, int companyID, boolean isDelete);

    /**
     * [Description]: Find a customer<br/>
     * [ Remarks ]:<br/>
     *
     * @param customerCode
     * @param companyID
     * @param customerID
     * @return A customer details
     */
    @Query(value = "SELECT * FROM customer c WHERE c.customer_code = :customerCode AND c.company_id = :companyID AND c.customer_id <> :customerID", nativeQuery = true)
    CustomerEntity findCustomerByCustomerodeAndCompanyID(@Param("customerCode") String customerCode, @Param("companyID") int companyID,
            @Param("customerID") Long customerID);
}
