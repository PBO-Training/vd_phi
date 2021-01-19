package com.brycen.hrm.masterservice.ms009001Delete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.CustomerEntity;

@Repository
public interface MS009001DeleteRepository extends JpaRepository<CustomerEntity, Long>{

}
