package com.brycen.hrm.masterservice.ms001002Create;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.UserEntity;

/**
 * [Description]: User Repository<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS001002CreateIUserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * [Description]: Find an user by username and companyID<br/>
     * [ Remarks ]:<br/>
     *
     * @param username
     * @param companyID
     * @return An user details
     */
    @Query(value = "SELECT * FROM user u WHERE u.user_name = :username AND u.company_id = :companyID", nativeQuery = true)
    UserEntity findUserByUsernameAndCompanyID(@Param("username") String username, @Param("companyID") int companyID);
}
