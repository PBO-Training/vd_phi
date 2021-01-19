package com.brycen.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brycen.hrm.entity.primaryKey.PrimaryKeyRoleScreenAction;

/**
 * [Description]:RoleScreenAction Entity<br>
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Entity
@Table(name = "role_screen_action")
@IdClass(PrimaryKeyRoleScreenAction.class)
public class RoleScreenActionEntity {
    /**
     * Role Id
     */
    @Id
    @ManyToOne 
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;
    
    /**
     * Screeb Id
     */
    @Id
    @ManyToOne 
    @JoinColumn(name = "screen_id", nullable = false)
    private ScreenEntity screen;
    
    /**
     * Action Id
     */
    @Id
    @ManyToOne 
    @JoinColumn(name = "action_id", nullable = false)
    private ActionEntity action;
    
    @Column(name = "access", columnDefinition = "boolean default false")
    private boolean access ;   
    
    public RoleScreenActionEntity()
    {
        
    }
    
    public RoleScreenActionEntity(RoleEntity role, ScreenEntity screen, ActionEntity action, boolean access)
    {
        this.role = role;
        this.screen = screen;
        this.action = action;
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

    public ActionEntity getAction() {
        return action;
    }

    public void setAction(ActionEntity action) {
        this.action = action;
    }

    public boolean getAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }
    
    
}
