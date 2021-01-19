package com.brycen.hrm.entity;

import java.util.List;

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
 * [Description]:Department Entity [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "department")
public class DepartmentEntity extends BaseEntity {

    /**
     * ID of Department
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long departmentID;

    /**
     * Department Name
     */
    @Column(name = "department_name", nullable = false)
    private String departmentName;

    /**
     * Department Code
     */
    @Column(name = "department_code", length = 40, nullable = false)
    private String departmentCode;

    /**
     * Department Description
     */
    @Column(name = "department_description", length = 2000)
    private String departmentDescription;

    /**
     * List Employee of Department
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<EmployeeEntity> listEmployee ;
    
    /**
     * List Project of Department
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<ProjectEntity>  listProject ;

    public DepartmentEntity() {

    }

    public DepartmentEntity(Long departmentID, String departmentName, String departmentCode, String departmentDescription, List<EmployeeEntity> listEmployee, List<ProjectEntity> listProject) {
        super();
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
        this.departmentDescription = departmentDescription;
        this.listEmployee = listEmployee;
        this.listProject = listProject;
    }

    public Long getDepartmentID() {
        return departmentID;
    }
    
    public void setDepartmentID(Long departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    public List<EmployeeEntity> getListEmployee() {
        return listEmployee;
    }

    public void setListEmployee(List<EmployeeEntity> listEmployee) {
        this.listEmployee = listEmployee;
    }

    public List<ProjectEntity> getListProject() {
        return listProject;
    }

    public void setListProject(List<ProjectEntity> listProject) {
        this.listProject = listProject;
    }
    
}
