package com.brycen.hrm.entity.primaryKey;

import java.io.Serializable;

/**
 * [Description]:Primary key of EmployeeProjectEntity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class PrimaryKeyEmpProject implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Employee Entity
     */
    private Long employee;
    /**
     * Project Entity
     */
    private Long project;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((employee == null) ? 0 : employee.hashCode());
        result = prime * result + ((project == null) ? 0 : project.hashCode());
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
        PrimaryKeyEmpProject other = (PrimaryKeyEmpProject) obj;
        if (employee == null) {
            if (other.employee != null)
                return false;
        } else if (!employee.equals(other.employee))
            return false;
        if (project == null) {
            if (other.project != null)
                return false;
        } else if (!project.equals(other.project))
            return false;
        return true;
    }

    public PrimaryKeyEmpProject(Long employee, Long project) {
        this.employee = employee;
        this.project = project;
    }

    public PrimaryKeyEmpProject() {
    }

}
