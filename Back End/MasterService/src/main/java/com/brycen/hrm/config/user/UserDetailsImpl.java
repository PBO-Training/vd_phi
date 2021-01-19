package com.brycen.hrm.config.user;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.brycen.hrm.entity.UserEntity;

public class UserDetailsImpl implements UserDetails{
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;
    
    private int companyID;

    private Collection<? extends GrantedAuthority> authorities;
    
    private List<Integer> listRoleValue;
    
    private String roleCode;
    
    //TODO
    public UserDetailsImpl(Long id, String username, String email, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    
    public UserDetailsImpl(Long id, String username,  String password, int companyID, Collection<? extends GrantedAuthority> authorities, List<Integer> listRoleValue) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.companyID = companyID;
        this.authorities = authorities;
        this.listRoleValue = listRoleValue;
    }
    
    public UserDetailsImpl(Long id, String username,  String password, int companyID, String roleCode) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.companyID = companyID;
        this.roleCode = roleCode;
    }

    public static UserDetailsImpl build(UserEntity user) {
//        List<GrantedAuthority> authorities = user.getListRole().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
//                .collect(Collectors.toList());
//        List<Integer> listRoleValue = user.getListRole().stream()
//                .map(role -> role.getRoleValue())
//                .collect(Collectors.toList());
//        return new UserDetailsImpl(user.getUserID(),user.getUsername(),user.getPassword(), user.getCompanyID(), authorities, listRoleValue);
        return new UserDetailsImpl(user.getUserID(),user.getUsername(),user.getPassword(), user.getCompanyID(), user.getRole().getRoleCode());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
 
    public Long getId() {
        return id;
    }

    public int getCompanyID() {
        return companyID;
    }

    public List<Integer> getListRoleValue() {
        return listRoleValue;
    }

    public void setListRoleValue(List<Integer> listRoleValue) {
        this.listRoleValue = listRoleValue;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
