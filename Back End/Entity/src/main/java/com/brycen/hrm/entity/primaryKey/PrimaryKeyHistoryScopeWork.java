package com.brycen.hrm.entity.primaryKey;

import java.io.Serializable;

/**
 * [Description]:Primary key of HistoryProcessProjectEntity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class PrimaryKeyHistoryScopeWork implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * HistoryWorkEntity
     */
    private Long historyWork;  
    /**
     * ProcessProjectEntity
     */
    private Long scopeWork;
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((historyWork == null) ? 0 : historyWork.hashCode());
        result = prime * result + ((scopeWork == null) ? 0 : scopeWork.hashCode());
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
        PrimaryKeyHistoryScopeWork other = (PrimaryKeyHistoryScopeWork) obj;
        if (historyWork == null) {
            if (other.historyWork != null)
                return false;
        } else if (!historyWork.equals(other.historyWork))
            return false;
        if (scopeWork == null) {
            if (other.scopeWork != null)
                return false;
        } else if (!scopeWork.equals(other.scopeWork))
            return false;
        return true;
    }
}
