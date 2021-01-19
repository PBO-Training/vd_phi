package com.brycen.hrm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.ActionEntity;

@Repository
public interface ActionIRepository extends JpaRepository<ActionEntity, Long> {
    /**
     * [Description]:Get list Action by CompanyID<br/>
     * [ Remarks ]:<br/>
     *
     * @param companyID
     * @param isDelete
     * @return
     */
    List<ActionEntity> findByCompanyIDAndIsDelete(int companyID, boolean isDelete);

    /**
     * [Description]:Get list Action<br/>
     * [ Remarks ]:<br/>
     *
     * @param actionCode
     * @param companyID
     * @param isDelete
     * @return
     */
    Optional<ActionEntity> findByActionCodeAndCompanyIDAndIsDelete(String actionCode, int companyID, boolean isDelete);
}
