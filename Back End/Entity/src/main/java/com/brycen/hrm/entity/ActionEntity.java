package com.brycen.hrm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:Action Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "action")
public class ActionEntity extends BaseEntity {

    /**
     * ID of Action
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id")
    private Long actionID;
    
    /**
     * Name of Action 
     */
    @Column(name = "action_name", nullable = false)
    private String actionName;

    /**
     * Code or name short of Action
     */
    @Column(name = "action_code", nullable = false, length = 40)
    private String actionCode;

    /**
     * List Group Action
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "action")
    private List<ActionGroupActionEntity> listGroupAction;
    
    public ActionEntity() {

    }

    public ActionEntity(String actionName, String actionCode) {
        this.actionName = actionName;
        this.actionCode = actionCode;
    }

    public Long getActionID() {
        return actionID;
    }

    public void setActionID(Long actionID) {
        this.actionID = actionID;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public List<ActionGroupActionEntity> getListGroupAction() {
        return listGroupAction;
    }

    public void setListGroupAction(List<ActionGroupActionEntity> listGroupAction) {
        this.listGroupAction = listGroupAction;
    }

}
