package com.brycen.hrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.UserEntity;

@Repository
public interface UserEntityIRepository extends JpaRepository<UserEntity, Long>{
    /**
     * [Description]:Find User bu UserID<br/>
     * [ Remarks ]:<br/>
     *
     * @param userID
     * @return
     */
    UserEntity findByUserID(Long userID);
    
    /**
     * [Description]:Find User by username and IsDelete<br/>
     * [ Remarks ]:<br/>
     *
     * @param username
     * @param isDelete
     * @return
     */
    UserEntity findByUsernameAndIsDelete(String username, boolean isDelete);
    
    /**
     * [Description]:Find User by username, companyID and IsDelete<br/>
     * [ Remarks ]:<br/>
     *
     * @param username
     * @param companyID
     * @param isDelete
     * @return
     */
    UserEntity findByUsernameAndCompanyIDAndIsDelete(String username, int companyID, boolean isDelete);
}
