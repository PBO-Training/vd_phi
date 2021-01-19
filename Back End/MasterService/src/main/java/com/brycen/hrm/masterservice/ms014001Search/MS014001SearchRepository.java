package com.brycen.hrm.masterservice.ms014001Search;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.StatusProjectEntity;

@Repository
public interface MS014001SearchRepository extends JpaRepository<StatusProjectEntity, Long> {

}
