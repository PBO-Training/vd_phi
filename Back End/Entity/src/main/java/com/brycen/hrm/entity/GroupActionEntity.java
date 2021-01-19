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
 * [Description]:<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "group_action")
public class GroupActionEntity extends BaseEntity {
    /**
     * ID of Group Action
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_action_id")
    private Long groupActionID;

    /**
     * Code or name short of Group Action
     */
    @Column(name = "group_action_code", nullable = false, length = 40)
    private String groupActionCode;

    /**
     * Name of Group Action
     */
    @Column(name = "group_action_name", nullable = false)
    private String groupActionName;

    /**
     * List Action
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupAction")
    private List<ActionGroupActionEntity> listAction;

    public GroupActionEntity() {
        super();
    }

    public GroupActionEntity(Long groupActionID, String groupActionCode, String groupActionName) {
        super();
        this.groupActionID = groupActionID;
        this.groupActionName = groupActionName;
        this.groupActionCode = groupActionCode;
    }

    public Long getGroupActionID() {
        return groupActionID;
    }

    public void setGroupActionID(Long groupActionID) {
        this.groupActionID = groupActionID;
    }

    public String getGroupActionName() {
        return groupActionName;
    }

    public void setGroupActionName(String groupActionName) {
        this.groupActionName = groupActionName;
    }

    public String getGroupActionCode() {
        return groupActionCode;
    }

    public void setGroupActionCode(String groupActionCode) {
        this.groupActionCode = groupActionCode;
    }

    public List<ActionGroupActionEntity> getListAction() {
        return listAction;
    }

    public void setListAction(List<ActionGroupActionEntity> listAction) {
        this.listAction = listAction;
    }
}
