package com.brycen.hrm.masterservice.ms000000Login;

import java.util.List;
import java.util.stream.Collectors;

import com.brycen.hrm.entity.ActionEntity;
import com.brycen.hrm.entity.ActionGroupActionEntity;
import com.brycen.hrm.entity.RoleScreenEntity;

/**
 * [Description]:Custom RoleScreen Response<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
public class MS000000RoleScreenResponse {
    /**
     * Access of Screen: true/false
     */
    private boolean access;

    /**
     * Screen ID
     */
    private Long screenID;

    /**
     * Screen Name
     */
    private String screenName;

    /**
     * Screen URL
     */
    private String screenUrl;

    /**
     * ID parent of screen
     */
    private Long screenLevel;

    /**
     * ID of group screen
     */
    private Long groupScreenID;

    /**
     * Name of group screen
     */
    private String groupScreenName;

    /**
     * Icon Menu of Group
     */
    private String groupIcon;
    
    /**
     * Index Menu of Group
     */
    private int groupIndex;

    /**
     * List action of screen
     */
    private List<MS000000RoleScreenActionResponse> listAction;

    public MS000000RoleScreenResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MS000000RoleScreenResponse(RoleScreenEntity roleScreenEntity) {
        super();
        this.access = roleScreenEntity.isAccess();
        this.screenID = roleScreenEntity.getScreen().getScreenID();
        this.screenName = roleScreenEntity.getScreen().getScreenName();
        this.screenUrl = roleScreenEntity.getScreen().getScreenUrl();
        this.screenLevel = roleScreenEntity.getScreen().getScreenLevel();
        this.groupScreenID = roleScreenEntity.getScreen().getGroupScreen() == null ? null : roleScreenEntity.getScreen().getGroupScreen().getGroupScreenID();
        this.groupScreenName = roleScreenEntity.getScreen().getGroupScreen() == null ? null
                : roleScreenEntity.getScreen().getGroupScreen().getGroupScreenName();
        this.groupIcon = roleScreenEntity.getScreen().getGroupScreen() == null ? null : roleScreenEntity.getScreen().getGroupScreen().getIconGroup();
        this.groupIndex = roleScreenEntity.getScreen().getGroupScreen() == null ? 0 : roleScreenEntity.getScreen().getGroupScreen().getGroupIndex();
        this.listAction = roleScreenEntity.getScreen().getListAction()
                .stream()
                .filter(u -> u.getRole().getRoleID() == roleScreenEntity.getRole().getRoleID() && isExistGroupAction(roleScreenEntity, u.getAction()))
                .map(MS000000RoleScreenActionResponse::new).collect(Collectors.toList());
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
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

    public List<MS000000RoleScreenActionResponse> getListAction() {
        return listAction;
    }

    public void setListAction(List<MS000000RoleScreenActionResponse> listAction) {
        this.listAction = listAction;
    }

    public Long getGroupScreenID() {
        return groupScreenID;
    }

    public void setGroupScreenID(Long groupScreenID) {
        this.groupScreenID = groupScreenID;
    }

    public String getGroupScreenName() {
        return groupScreenName;
    }

    public void setGroupScreenName(String groupScreenName) {
        this.groupScreenName = groupScreenName;
    }

    public String getGroupIcon() {
        return groupIcon;
    }

    public void setGroupIcon(String groupIcon) {
        this.groupIcon = groupIcon;
    }

    public int getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(int groupIndex) {
        this.groupIndex = groupIndex;
    }
    
    private boolean isExistGroupAction(RoleScreenEntity roleScreenEntity, ActionEntity action) {        
        for (ActionGroupActionEntity actionGroupActionEntity : action.getListGroupAction()) {
            if(roleScreenEntity.getScreen().getGroupAction().getGroupActionCode().equals(actionGroupActionEntity.getGroupAction().getGroupActionCode()))
                return true;
        }
        return false;
    }
}
