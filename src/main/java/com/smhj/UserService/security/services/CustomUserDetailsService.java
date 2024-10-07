package com.smhj.UserService.security.services;

import com.smhj.UserService.models.User;
import com.smhj.UserService.repositories.UserRepository;
import com.smhj.UserService.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);

        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User with email: " + username + " doesn't exist");
        }
        return new CustomUserDetails(optionalUser.get());
    }
}
