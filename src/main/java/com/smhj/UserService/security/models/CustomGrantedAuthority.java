package com.smhj.UserService.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.smhj.UserService.models.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


@Getter
@Setter
@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;

    public CustomGrantedAuthority(){
    }

    public CustomGrantedAuthority(Role role){
        this.authority = role.getValue();
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
