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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brycen.hrm.common.base.BaseEntity;

/**
 * [Description]:Role Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleID;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "role_code", nullable = false, length = 40)
    private String roleCode;
    
    @Column(name = "role_value")
    private int roleValue;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<RoleScreenEntity> listRoleScreen ;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<UserEntity> listUser = new HashSet<>();  
    
    public RoleEntity() {

    }

    public RoleEntity(String roleName, String roleCode) {
        this.roleName = roleName;
        
        this.roleCode = roleCode;
    }

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

//    public Set<UserRoleEntity> getListUserRole() {
//        return listUserRole;
//    }
//
//    public void setListUserRole(Set<UserRoleEntity> listUserRole) {
//        this.listUserRole = listUserRole;
//    }

    public int getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(int roleValue) {
        this.roleValue = roleValue;
    }

    public List<RoleScreenEntity> getListRoleScreen() {
        return listRoleScreen;
    }

    public void setListRoleScreen(List<RoleScreenEntity> listRoleScreen) {
        this.listRoleScreen = listRoleScreen;
    }

    public Set<UserEntity> getListUser() {
        return listUser;
    }

    public void setListUser(Set<UserEntity> listUser) {
        this.listUser = listUser;
    }
    
}
