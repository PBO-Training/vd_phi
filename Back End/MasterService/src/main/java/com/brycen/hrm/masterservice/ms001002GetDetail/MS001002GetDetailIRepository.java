package com.brycen.hrm.masterservice.ms001002GetDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.UserEntity;

/**
 * [Description]: Get Details Repository for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS001002GetDetailIRepository extends JpaRepository<UserEntity, Long> {

    /**
     * [Description]: Find an user by userID<br/>
     * [ Remarks ]:<br/>
     *
     * @param userID
     * @param companyID
     * @param isDelete
     * @return An user details
     */
    Optional<UserEntity> findByUserIDAndCompanyIDAndIsDelete(long userID, int companyID, boolean isDelete);
}
