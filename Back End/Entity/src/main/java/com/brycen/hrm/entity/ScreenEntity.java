package com.brycen.hrm.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:Screen Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "screen")
public class ScreenEntity extends BaseEntity {
    /**
     * Screen Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_id")
    private Long screenID;

    /**
     * Screen Name
     */
    @Column(name = "screen_name", nullable = false)
    private String screenName;

    /**
     * Screen Code
     */
    @Column(name = "screen_code", nullable = false, length = 40)
    private String screenCode;

    /**
     * Screen Url
     */
    @Column(name = "screen_url")
    private String screenUrl;

    /**
     * Parent Screen
     */
    @Column(name = "screen_level")
    private Long screenLevel;

    /**
     * Customer Id
     */
    @ManyToOne
    @JoinColumn(name = "group_screen_id", nullable = false)
    private GroupScreenEntity groupScreen;

    /**
     * Group Action Entity
     */
    @ManyToOne
    @JoinColumn(name = "group_action_id", nullable = false)
    private GroupActionEntity groupAction;

    /**
     * List Role Screen
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "screen")
    private Set<RoleScreenEntity> listRoleScreen = new HashSet<>();

    /**
     * List RoleScreenAction
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "screen")
    private List<RoleScreenActionEntity> listAction;

    public ScreenEntity() {

    }

    public ScreenEntity(String screenName, String screenCode, String screenUrl) {
        this.screenName = screenName;
        this.screenCode = screenCode;
        this.screenUrl = screenUrl;
    }

    public Long getScreenID() {
        return screenID;
    }

    public void setScreenID(Long screenID) {
        this.screenID = screenID;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getScreenCode() {
        return screenCode;
    }

    public void setScreenCode(String screenCode) {
        this.screenCode = screenCode;
    }

    public String getScreenUrl() {
        return screenUrl;
    }

    public void setScreenUrl(String screenUrl) {
        this.screenUrl = screenUrl;
    }

    public Long getScreenLevel() {
        return screenLevel;
    }

    public void setScreenLevel(Long screenLevel) {
        this.screenLevel = screenLevel;
    }

    public Set<RoleScreenEntity> getListRoleScreen() {
        return listRoleScreen;
    }

    public void setListRoleScreen(Set<RoleScreenEntity> listRoleScreen) {
        this.listRoleScreen = listRoleScreen;
    }

    public List<RoleScreenActionEntity> getListAction() {
        return listAction;
    }

    public void setListAction(List<RoleScreenActionEntity> listAction) {
        this.listAction = listAction;
    }

    public GroupScreenEntity getGroupScreen() {
        return groupScreen;
    }

    public void setGroupScreen(GroupScreenEntity groupScreen) {
        this.groupScreen = groupScreen;
    }

    public GroupActionEntity getGroupAction() {
        return groupAction;
    }

    public void setGroupAction(GroupActionEntity groupAction) {
        this.groupAction = groupAction;
    }

}
