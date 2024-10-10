package com.smhj.UserService.models;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Table(name = "users")
@Entity
@Getter
@Setter
public class User extends BaseModel{

    private String name;
    private String email;
    private String hashedPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    private boolean isVerified;

}
