package com.smhj.UserService.Controller;


import com.smhj.UserService.dto.LoginRequestDto;
import com.smhj.UserService.dto.LogoutRequestDto;
import com.smhj.UserService.dto.SignupRequestDto;
import com.smhj.UserService.dto.UserDto;
import com.smhj.UserService.exceptions.InvalidPasswordException;
import com.smhj.UserService.exceptions.InvalidTokenException;
import com.smhj.UserService.exceptions.UserNotFoundException;
import com.smhj.UserService.models.Token;
import com.smhj.UserService.models.User;
import com.smhj.UserService.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignupRequestDto requestDto){
        User user = userService.signUp(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());
        return UserDto.from(user);
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto requestDto) throws UserNotFoundException, InvalidPasswordException {
        return userService.login(requestDto.getEmail(), requestDto.getPassword());
    }

    @PostMapping("/log-out")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto requestDto) throws InvalidTokenException {
        userService.logout(requestDto.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/logOut")
//    public ResponseEntity<Void> logout() {
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping("/user/{token}")
    public UserDto validateToken(@PathVariable String token) throws InvalidTokenException {
        User user = userService.ValidateToken(token);
        return UserDto.from(user);
    }




}
