package com.brycen.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.GroupScreenEntity;

@Repository
public interface GroupScreenIRepository extends JpaRepository<GroupScreenEntity, Long> {
    /**
     * [Description]:Get list GroupAction by CompanyID<br/>
     * [ Remarks ]:<br/>
     *
     * @param companyID
     * @param isDelete
     * @return
     */
    List<GroupScreenEntity> findByCompanyIDAndIsDeleteOrderByGroupIndex(int companyID, boolean isDelete);
}
