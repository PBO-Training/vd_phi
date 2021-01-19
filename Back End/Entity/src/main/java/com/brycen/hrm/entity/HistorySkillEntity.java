package com.brycen.hrm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;
import com.brycen.hrm.entity.primaryKey.PrimaryKeyHistorySkill;

/**
 * [Description]:EmployeeSkill Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "history_skill")
@IdClass(PrimaryKeyHistorySkill.class)
public class HistorySkillEntity extends BaseEntity {
	/**
	 * History ID
	 */
	@Id
	@ManyToOne
	@JoinColumn(name = "history_work_id", nullable = false)
	private HistoryWorkEntity historyWork;

	/**
	 * Skill Id
	 */
	@Id
	@ManyToOne
	@JoinColumn(name = "skill_id", nullable = false)
	private SkillEntity skill;

	public HistorySkillEntity() {

	}

	public HistorySkillEntity(HistoryWorkEntity history, SkillEntity skill) {
		super();
		this.historyWork = history;
		this.skill = skill;
	}

	public HistoryWorkEntity getHistory() {
		return historyWork;
	}

	public void setHistory(HistoryWorkEntity history) {
		this.historyWork = history;
	}

	public SkillEntity getSkill() {
		return skill;
	}

	public void setSkill(SkillEntity skill) {
		this.skill = skill;
	}

}
