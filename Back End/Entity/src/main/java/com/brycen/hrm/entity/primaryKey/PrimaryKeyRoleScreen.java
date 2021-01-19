package com.brycen.hrm.entity.primaryKey;

import java.io.Serializable;

/**
 * [Description]:Primary key of RoleScreenEntity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class PrimaryKeyRoleScreen implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Screen Entity
     */
    private Long screen;  
    /**
     * Role Entity
     */
    private Long role;
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((screen == null) ? 0 : screen.hashCode());
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
        PrimaryKeyRoleScreen other = (PrimaryKeyRoleScreen) obj;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        if (screen == null) {
            if (other.screen != null)
                return false;
        } else if (!screen.equals(other.screen))
            return false;
        return true;
    }
    
    
}
