package com.brycen.hrm.masterservice.ms009001Search;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.CustomerEntity;

@Repository
public interface MS009001SearchRepository extends JpaRepository<CustomerEntity, Long> {

}
