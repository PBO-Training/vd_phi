package com.brycen.hrm.entity.primaryKey;

import java.io.Serializable;

/**
 * [Description]: Primary key of HistorySkillEntity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class PrimaryKeyHistorySkill implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * HistoryWorkEntity
     */
    private Long historyWork;
    /**
     * SkillEntity
     */
    private Long skill;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((historyWork == null) ? 0 : historyWork.hashCode());
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
        PrimaryKeyHistorySkill other = (PrimaryKeyHistorySkill) obj;
        if (historyWork == null) {
            if (other.historyWork != null)
                return false;
        } else if (!historyWork.equals(other.historyWork))
            return false;
        if (skill == null) {
            if (other.skill != null)
                return false;
        } else if (!skill.equals(other.skill))
            return false;
        return true;
    }

    public PrimaryKeyHistorySkill(Long historyWork, Long skill) {
        this.historyWork = historyWork;
        this.skill = skill;
    }

    public PrimaryKeyHistorySkill() {
    }

}
