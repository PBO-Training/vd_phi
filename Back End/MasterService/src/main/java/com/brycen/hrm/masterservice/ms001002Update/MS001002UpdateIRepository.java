package com.brycen.hrm.masterservice.ms001002Update;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.UserEntity;

/**
 * [Description]: Update Repository for User Master Table<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS001002UpdateIRepository extends JpaRepository<UserEntity, Long> {

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

    /**
     * [Description]: Find an user by username<br/>
     * [ Remarks ]:<br/>
     *
     * @param username
     * @param companyID
     * @param userID
     * @return An user details
     */
    @Query(value = "SELECT * FROM user u WHERE u.user_name = :username AND u.company_id = :companyID AND u.user_id <> :userID", nativeQuery = true)
    UserEntity findUserByUsernameAndCompanyID(@Param("username") String username, @Param("companyID") int companyID, @Param("userID") Long userID);
}
