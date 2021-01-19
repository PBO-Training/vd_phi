package com.brycen.hrm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brycen.hrm.entity.primaryKey.PrimaryKeyActionGroupAction;

/**
 * [Description]:<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "action_group_action")
@IdClass(PrimaryKeyActionGroupAction.class)
public class ActionGroupActionEntity {
    /**
     * Action ID
     */
    @Id
    @ManyToOne 
    @JoinColumn(name = "action_id", nullable = false)
    private ActionEntity action;
    
    /**
     * Group Action ID
     */
    @Id
    @ManyToOne 
    @JoinColumn(name = "group_action_id", nullable = false)
    private GroupActionEntity groupAction;

    public ActionGroupActionEntity() {
        super();
    }

    public ActionGroupActionEntity(ActionEntity action, GroupActionEntity groupAction) {
        super();
        this.action = action;
        this.groupAction = groupAction;
    }

    public ActionEntity getAction() {
        return action;
    }

    public void setAction(ActionEntity action) {
        this.action = action;
    }

    public GroupActionEntity getGroupAction() {
        return groupAction;
    }

    public void setGroupAtion(GroupActionEntity groupAction) {
        this.groupAction = groupAction;
    }
    
}
