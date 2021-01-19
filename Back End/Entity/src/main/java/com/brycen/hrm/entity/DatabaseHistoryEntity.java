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
 * [Description]:DatabaseHistory Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "database_history")
public class DatabaseHistoryEntity extends BaseEntity {
    /**
     * Datebase History ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "database_history_id")
    private Long databaseHistoryID;

    /**
     * Datebase History Name
     */
    @Column(name = "database_history_name", nullable = false)
    private String databaseHistoryName;

    /**
     * Datebase History Code
     */
    @Column(name = "database_history_code", nullable = false, length = 40)
    private String databaseHistoryCode;

    /**
     * List HistoryWork
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "databaseHistory")
    private Set<HistoryWorkEntity> listHistoryWork = new HashSet<>();
    
    public DatabaseHistoryEntity() {

    }

    public DatabaseHistoryEntity(String databaseHistoryName, String databaseHistoryCode) {
        this.databaseHistoryName = databaseHistoryName;
        this.databaseHistoryCode = databaseHistoryCode;
    }

    public Long getDatabaseHistoryID() {
        return databaseHistoryID;
    }

    public void setDatabaseHistoryID(Long databaseHistoryID) {
        this.databaseHistoryID = databaseHistoryID;
    }

    public String getDatabaseHistoryName() {
        return databaseHistoryName;
    }

    public void setDatabaseHistoryName(String databaseHistoryName) {
        this.databaseHistoryName = databaseHistoryName;
    }

    public String getDatabaseHistoryCode() {
        return databaseHistoryCode;
    }

    public void setDatabaseHistoryCode(String databaseHistoryCode) {
        this.databaseHistoryCode = databaseHistoryCode;
    }

    public Set<HistoryWorkEntity> getListHistoryWork() {
        return listHistoryWork;
    }

    public void setListHistoryWork(Set<HistoryWorkEntity> listHistoryWork) {
        this.listHistoryWork = listHistoryWork;
    }
    
    
}
