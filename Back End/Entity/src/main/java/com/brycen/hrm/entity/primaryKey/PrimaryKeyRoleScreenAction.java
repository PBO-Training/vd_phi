package com.brycen.hrm.entity.primaryKey;

import java.io.Serializable;

/**
 * [Description]:PrimaryKey of RoleScreenAction<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class PrimaryKeyRoleScreenAction implements Serializable{

    private static final long serialVersionUID = 1L;
    
    /**
     * Screen Entity
     */
    private Long screen;  
    
    /**
     * Role Entity
     */
    private Long role;
    
    /**
     * Action Entity
     */
    private Long action;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((action == null) ? 0 : action.hashCode());
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
        PrimaryKeyRoleScreenAction other = (PrimaryKeyRoleScreenAction) obj;
        if (action == null) {
            if (other.action != null)
                return false;
        } else if (!action.equals(other.action))
            return false;
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
