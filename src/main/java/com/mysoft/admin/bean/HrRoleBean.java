package com.mysoft.admin.bean;

import com.mysoft.admin.db.entity.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 描述：
 *
 * @Auth yang.m.zhang
 * @Date 9/18/2018 2:20 PM
 * @Version 1.0
 */
@Setter
@Getter
public class HrRoleBean implements UserDetails {

    private Integer id;

    private String name;

    private String phone;

    private String telephone;

    private String address;

   private Integer enable;

    private String username;

    private String password;

    private String userface;

    private String remark;

    private List<RoleBean> roleList;

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
        return enable > 0;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleBean role : roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
