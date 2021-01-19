package com.brycen.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:PosionProject Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "position_project")
public class PositionProjectEntity extends BaseEntity {
    /**
     * Position Project Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_project_id")
    private Long positionProjectID;

    /**
     * Position Project Name
     */
    @Column(name = "position_project_name", nullable = false)
    private String positionProjectName;

    /**
     * Position Project Code
     */
    @Column(name = "position_project_code", nullable = false, length = 40)
    private String positionProjectCode;

    /**
     * Position Project Description
     */
    @Column(name = "position_project_description", length = 2000)
    private String positionProjectDescription;

    public PositionProjectEntity() {

    }

    public PositionProjectEntity(String positionProjectName, String positionProjectCode, String positionProjectDescription) {
        this.positionProjectName = positionProjectName;
        this.positionProjectCode = positionProjectCode;
        this.positionProjectDescription = positionProjectDescription;
    }

    public Long getPositionProjectID() {
        return positionProjectID;
    }

    public void setPositionProjectID(Long positionProjectID) {
        this.positionProjectID = positionProjectID;
    }

    public String getPositionProjectName() {
        return positionProjectName;
    }

    public void setPositionProjectName(String positionProjectName) {
        this.positionProjectName = positionProjectName;
    }

    public String getPositionProjectCode() {
        return positionProjectCode;
    }

    public void setPositionProjectCode(String positionProjectCode) {
        this.positionProjectCode = positionProjectCode;
    }

    public String getPositionProjectDescription() {
        return positionProjectDescription;
    }

    public void setPositionProjectDescription(String positionProjectDescription) {
        this.positionProjectDescription = positionProjectDescription;
    }

}
