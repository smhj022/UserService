package com.smhj.UserService.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class SignupRequestDto {
    private String name;
    private String email;
    private String password;
}
