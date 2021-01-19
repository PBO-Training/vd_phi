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
 * [Description]:Group Screen Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "group_screen")
public class GroupScreenEntity extends BaseEntity {

    /**
     * ID of Group Screen
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_screen_id")
    private Long groupScreenID;
    
    /**
     * Name of Group Screen
     */
    @Column(name = "group_screen_name", nullable = false)
    private String groupScreenName;

    /**
     * Code or name short of Group Screen
     */
    @Column(name = "group_screen_code", nullable = false, length = 40)
    private String groupScreenCode;
    
    /**
     * Icon Menu of Group Screen
     */
    @Column(name = "icon")
    private String iconGroup;
    
    /**
     * Index Menu of Group Screen
     */
    @Column(name = "group_index")
    private int groupIndex;
    
    /**
     * List Screen
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupScreen")
    private List<ScreenEntity> listProject ;
    
    
    public GroupScreenEntity() {
        super();
    }

    public GroupScreenEntity(Long groupScreenID, String groupScreenName, String groupScreenCode, String iconGroup, int groupIndex,
            List<ScreenEntity> listProject) {
        super();
        this.groupScreenID = groupScreenID;
        this.groupScreenName = groupScreenName;
        this.groupScreenCode = groupScreenCode;
        this.iconGroup = iconGroup;
        this.groupIndex = groupIndex;
        this.listProject = listProject;
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

    public String getGroupScreenCode() {
        return groupScreenCode;
    }

    public void setGroupScreenCode(String groupScreenCode) {
        this.groupScreenCode = groupScreenCode;
    }

    public List<ScreenEntity> getListProject() {
        return listProject;
    }

    public void setListProject(List<ScreenEntity> listProject) {
        this.listProject = listProject;
    }

    public String getIconGroup() {
        return iconGroup;
    }

    public void setIconGroup(String iconGroup) {
        this.iconGroup = iconGroup;
    }

    public int getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(int groupIndex) {
        this.groupIndex = groupIndex;
    }
}
