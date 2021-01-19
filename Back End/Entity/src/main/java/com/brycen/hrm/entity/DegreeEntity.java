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
 * [Description]:Degree Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 2.0
 */
@Entity
@Table(name = "degree")
public class DegreeEntity extends BaseEntity {
    /**
     * ID of Degree
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "degree_id")
    private Long degreeID;

    /**
     * Code of Degree
     */
    @Column(name = "degree_code", nullable = false, length = 40)
    private String degreeCode;

    /**
     * Name of Degree
     */
    @Column(name = "degree_name", nullable = false, length = 255)
    private String degreeName;

    /**
     * Description of Degree
     */
    @Column(name = "degree_description", length = 2000)
    private String degreeDescription;

    /**
     * List Employee of Degree
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "degree")
    private List<EmployeeEntity> listEmployee;

    public DegreeEntity() {

    }

    public DegreeEntity(Long degreeID, String degreeName, String degreeCode, String degreeDescription, List<EmployeeEntity> listEmployee) {
        super();
        this.degreeID = degreeID;
        this.degreeName = degreeName;
        this.degreeCode = degreeCode;
        this.degreeDescription = degreeDescription;
        // this.listEmployee = listEmployee;
    }

    public Long getDegreeID() {
        return degreeID;
    }

    public void setDegreeID(Long degreeID) {
        this.degreeID = degreeID;
    }

    public String getDegreeCode() {
        return degreeCode;
    }

    public void setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getDegreeDescription() {
        return degreeDescription;
    }

    public void setDegreeDescription(String degreeDescription) {
        this.degreeDescription = degreeDescription;
    }

    // public List<EmployeeEntity> getListEmployee() {
    // return listEmployee;
    // }
    //
    // public void setListEmployee(List<EmployeeEntity> listEmployee) {
    // this.listEmployee = listEmployee;
    // }

}
