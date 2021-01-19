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
 * [Description]:Field Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "field")
public class FieldEntity extends BaseEntity {

    /**
     * ID of field
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id")
    private Long fieldID;

    /**
     * field Name
     */
    @Column(name = "field_name", nullable = false)
    private String fieldName;

    /**
     * field Code
     */
    @Column(name = "field_code", length = 40, nullable = false)
    private String fieldCode;

    /**
     * field Description
     */
    @Column(name = "field_description", length = 2000)
    private String fieldDescription;

    /**
     * List TrackerDetail
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "field")
    private Set<TrackerDetailEntity> listTrackerDetail = new HashSet<>();

    public FieldEntity(Long fieldID, String fieldName, String fieldCode, String fieldDescription, Set<TrackerDetailEntity> listTrackerDetail) {
        this.fieldID = fieldID;
        this.fieldName = fieldName;
        this.fieldCode = fieldCode;
        this.fieldDescription = fieldDescription;
        this.listTrackerDetail = listTrackerDetail;
    }

    public FieldEntity() {
    }

    public Long getFieldID() {
        return fieldID;
    }

    public void setFieldID(Long fieldID) {
        this.fieldID = fieldID;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getFieldDescription() {
        return fieldDescription;
    }

    public void setFieldDescription(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }

    public Set<TrackerDetailEntity> getListTrackerDetail() {
        return listTrackerDetail;
    }

    public void setListTrackerDetail(Set<TrackerDetailEntity> listTrackerDetail) {
        this.listTrackerDetail = listTrackerDetail;
    }

}
