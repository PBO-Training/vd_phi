package com.brycen.hrm.entity.primaryKey;

import java.io.Serializable;

/**
 * [Description]:Primary key of EmployeeVacationTypeEntity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class PrimaryKeyEmpVacationType implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Employee Entity
     */
    private Long employee;  
    /**
     * VacationType Entity
     */
    private Long vacationType;
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((employee == null) ? 0 : employee.hashCode());
        result = prime * result + ((vacationType == null) ? 0 : vacationType.hashCode());
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
        PrimaryKeyEmpVacationType other = (PrimaryKeyEmpVacationType) obj;
        if (employee == null) {
            if (other.employee != null)
                return false;
        } else if (!employee.equals(other.employee))
            return false;
        if (vacationType == null) {
            if (other.vacationType != null)
                return false;
        } else if (!vacationType.equals(other.vacationType))
            return false;
        return true;
    }

}
