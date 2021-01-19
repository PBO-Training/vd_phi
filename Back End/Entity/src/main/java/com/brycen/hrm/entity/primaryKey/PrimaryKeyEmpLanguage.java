package com.brycen.hrm.entity.primaryKey;

import java.io.Serializable;

/**
 * [Description]: Primary key of EmployeeLanguageEntity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class PrimaryKeyEmpLanguage implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * EmployeeEntity
     */
    private Long employee;  
    /**
     * LanguageEntity
     */
    private Long language;
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((employee == null) ? 0 : employee.hashCode());
        result = prime * result + ((language == null) ? 0 : language.hashCode());
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
        PrimaryKeyEmpLanguage other = (PrimaryKeyEmpLanguage) obj;
        if (employee == null) {
            if (other.employee != null)
                return false;
        } else if (!employee.equals(other.employee))
            return false;
        if (language == null) {
            if (other.language != null)
                return false;
        } else if (!language.equals(other.language))
            return false;
        return true;
    }
    
}
