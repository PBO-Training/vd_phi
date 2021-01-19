package com.brycen.hrm.config.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>  {
    Optional<UserEntity> findByUsernameAndCompanyID(String username, int companyID);
    Boolean existsByUsername(String username);
}
