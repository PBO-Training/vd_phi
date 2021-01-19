package com.brycen.hrm.entity.primaryKey;

import java.io.Serializable;

/**
 * [Description]:<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class PrimaryKeyActionGroupAction implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Action Entity
     */
    private Long action;

    /**
     * Group Action Entity
     */
    private Long groupAction;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((action == null) ? 0 : action.hashCode());
        result = prime * result + ((groupAction == null) ? 0 : groupAction.hashCode());
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
        PrimaryKeyActionGroupAction other = (PrimaryKeyActionGroupAction) obj;
        if (action == null) {
            if (other.action != null)
                return false;
        } else if (!action.equals(other.action))
            return false;
        if (groupAction == null) {
            if (other.groupAction != null)
                return false;
        } else if (!groupAction.equals(other.groupAction))
            return false;
        return true;
    }

}
