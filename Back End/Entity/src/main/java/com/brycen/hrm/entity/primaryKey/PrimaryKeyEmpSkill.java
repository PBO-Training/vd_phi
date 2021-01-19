package com.brycen.hrm.entity.primaryKey;

import java.io.Serializable;

/**
 * [Description]:Primary key of EmployeeSkillEntity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class PrimaryKeyEmpSkill implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * EmployeeEntity
     */
    private Long employee;
    /**
     * SkillEntity
     */
    private Long skill;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((employee == null) ? 0 : employee.hashCode());
        result = prime * result + ((skill == null) ? 0 : skill.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PrimaryKeyEmpSkill other = (PrimaryKeyEmpSkill) obj;
        if (employee == null) {
            if (other.employee != null)
                return false;
        } else if (!employee.equals(other.employee))
            return false;
        if (skill == null) {
            if (other.skill != null)
                return false;
        } else if (!skill.equals(other.skill))
            return false;
        return true;
    }

    public Long getEmployee() {
        return employee;
    }

    public void setEmployee(Long employee) {
        this.employee = employee;
    }

    public Long getSkill() {
        return skill;
    }

    public void setSkill(Long skill) {
        this.skill = skill;
    }

    public PrimaryKeyEmpSkill(Long employee, Long skill) {
        this.employee = employee;
        this.skill = skill;
    }

    public PrimaryKeyEmpSkill() {
    }

}
