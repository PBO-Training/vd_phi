package com.brycen.hrm.masterservice.ms012001Search;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.VacationTypeEntity;

/**
 * [Description]: Interface search repository contain method to make to easy crud<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Repository
public interface MS012001SearchRepository extends JpaRepository<VacationTypeEntity, Long> {

    /**
     * [Description]: Find list vacation type by companyID and isDelete<br/>
     * [ Remarks ]:<br/>
     *
     * @param companyID
     * @param isDelete
     * @return list vacation type
     */
    List<VacationTypeEntity> findAllByCompanyIDAndIsDelete(int companyID, boolean isDelete);
}
