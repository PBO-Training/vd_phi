package com.brycen.hrm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.ScreenEntity;

@Repository
public interface ScreenIRepository extends JpaRepository<ScreenEntity, Long> {
    /**
     * [Description]:Get list Screen by CompanyID<br/>
     * [ Remarks ]:<br/>
     *
     * @param companyID
     * @param isDelete
     * @return
     */
    List<ScreenEntity> findByCompanyIDAndIsDeleteOrderByScreenCode(int companyID, boolean isDelete);

    /**
     * [Description]:Find Screen By screenCode, companyID and IsDelete<br/>
     * [ Remarks ]:<br/>
     *
     * @param screenCode
     * @param companyID
     * @param isDelete
     * @return
     */
    Optional<ScreenEntity> findByScreenCodeAndCompanyIDAndIsDelete(String screenCode, int companyID, boolean isDelete);
}
