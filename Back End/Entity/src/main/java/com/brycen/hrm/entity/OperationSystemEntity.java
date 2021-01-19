package com.brycen.hrm.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:OperationSystem Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "operation_system")
public class OperationSystemEntity extends BaseEntity{

    /**
     * Operation System Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_system_id")
    private Long operationSystemID;

    /**
     * Operation System Name
     */
    @Column(name = "operation_system_name", nullable = false)
    private String operationSystemName;

    /**
     * Operation System Code
     */
    @Column(name = "operation_system_code", nullable = false, length = 40)
    private String operationSystemCode;
    
    /**
     * List History Work
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "operationSystem")
    private Set<HistoryWorkEntity> listHistoryWork = new HashSet<>();
    
    public OperationSystemEntity() {

    }

    public OperationSystemEntity(String operationSystemName, String operationSystemCode) {
        this.operationSystemName = operationSystemName;
        this.operationSystemCode = operationSystemCode;
    }

    public Long getOperationSystemID() {
        return operationSystemID;
    }

    public void setOperationSystemID(Long operationSystemID) {
        this.operationSystemID = operationSystemID;
    }

    public String getOperationSystemName() {
        return operationSystemName;
    }

    public void setOperationSystemName(String operationSystemName) {
        this.operationSystemName = operationSystemName;
    }

    public String getOperationSystemCode() {
        return operationSystemCode;
    }

    public void setOperationSystemCode(String operationSystemCode) {
        this.operationSystemCode = operationSystemCode;
    }
    
    
}
