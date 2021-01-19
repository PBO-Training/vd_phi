package com.brycen.hrm.masterservice.ms015001Search;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brycen.hrm.entity.ContractTypeEntity;

@Repository
public interface MS015001SearchRepository extends JpaRepository<ContractTypeEntity, Long> {
    /**
     * [Description]: Find all contract with condition companyID and isDelete<br/>
     * [ Remarks ]:<br/>
     *
     * @param companyID
     * @param isDelete
     * @return
     */
    List<ContractTypeEntity> findAllByCompanyIDAndIsDelete(int companyID, boolean isDelete);
}
