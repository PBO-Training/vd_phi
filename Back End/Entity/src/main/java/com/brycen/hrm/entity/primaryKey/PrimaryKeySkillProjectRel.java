package com.brycen.hrm.entity.primaryKey;

import java.io.Serializable;

/**
 * [Description]:Primary key of Skill of project relationship<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class PrimaryKeySkillProjectRel implements Serializable {
    /**
     * Unique ID of class
     */
    private static final long serialVersionUID = 1L;
    /**
     * Project entity
     */
    private Long project;
    /**
     * Skill project entity
     */
    private Long skillProject;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((project == null) ? 0 : project.hashCode());
        result = prime * result + ((skillProject == null) ? 0 : skillProject.hashCode());
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
        PrimaryKeySkillProjectRel other = (PrimaryKeySkillProjectRel) obj;
        if (project == null) {
            if (other.project != null)
                return false;
        } else if (!project.equals(other.project))
            return false;
        if (skillProject == null) {
            if (other.skillProject != null)
                return false;
        } else if (!skillProject.equals(other.skillProject))
            return false;
        return true;
    }

    public Long getProject() {
        return project;
    }

    public void setProject(Long project) {
        this.project = project;
    }

    public Long getSkillProject() {
        return skillProject;
    }

    public void setSkillProject(Long skillProject) {
        this.skillProject = skillProject;
    }
}
