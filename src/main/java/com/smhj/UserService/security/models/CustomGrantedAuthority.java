package com.smhj.UserService.security.models;

import com.smhj.UserService.models.Role;
import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;

    public CustomGrantedAuthority(Role role){
        this.authority = role.getValue();
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
