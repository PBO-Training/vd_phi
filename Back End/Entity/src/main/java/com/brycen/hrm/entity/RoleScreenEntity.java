package com.brycen.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brycen.hrm.entity.primaryKey.PrimaryKeyRoleScreen;

/**
 * [Description]:RoleScreen Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "role_screen")
@IdClass(PrimaryKeyRoleScreen.class)
public class RoleScreenEntity {
    /**
     * Role Id
     */
    @Id
    @ManyToOne 
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;
    
    /**
     * Id Screen
     */
    @Id
    @ManyToOne 
    @JoinColumn(name = "screen_id", nullable = false)
    private ScreenEntity screen;
    
    @Column(name = "access", columnDefinition = "boolean default false")
    private boolean access ;   
    
    public RoleScreenEntity()
    {
        
    }
    
    public RoleScreenEntity(RoleEntity role, ScreenEntity screen, boolean access)
    {
        this.role = role;
        this.screen = screen;
        this.access = access;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public ScreenEntity getScreen() {
        return screen;
    }

    public void setScreen(ScreenEntity screen) {
        this.screen = screen;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }
    
}
