package com.smhj.UserService.services;


import com.smhj.UserService.exceptions.InvalidPasswordException;
import com.smhj.UserService.exceptions.InvalidTokenException;
import com.smhj.UserService.exceptions.UserNotFoundException;
import com.smhj.UserService.models.User;
import com.smhj.UserService.models.Token;
import com.smhj.UserService.repositories.TokenRepository;
import com.smhj.UserService.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    UserService(BCryptPasswordEncoder bCryptPasswordEncoder,
                UserRepository userRepository,
                TokenRepository tokenRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }


    public User signUp(String name, String email, String password) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        user.setVerified(true);
        return userRepository.save(user);
    }

    public Token login(String email, String password) throws UserNotFoundException, InvalidPasswordException {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with email " + email + " doesn't exist");
        }

        User user = optionalUser.get();

        if (!bCryptPasswordEncoder.matches(password, user.getHashedPassword())) {
            throw new InvalidPasswordException("Password is Incorrect");
        }
        Token token = generateToken(user);
        return tokenRepository.save(token);

    }

    public void logout(String tokenValue) throws InvalidTokenException {
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedAndExpiryGreaterThan(
                tokenValue, false, new Date());

        if(optionalToken.isEmpty()){
            throw new InvalidTokenException("Token is Invalid. Please Login again");
        }
        Token token = optionalToken.get();

        token.setDeleted(true);
        tokenRepository.save(token);
    }

    public User ValidateToken(String token) throws InvalidTokenException {
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedAndExpiryGreaterThan(
                token, false, new Date());

        if(optionalToken.isEmpty()){
            throw new InvalidTokenException("Token is invalid");
        }

        return optionalToken.get().getUser();
    }

    private Token generateToken(User user){
        LocalDate currentDate = LocalDate.now();
        LocalDate thirtyDaysLater = currentDate.plusDays(30);

        Date expiryTime = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Token token = new Token();
        token.setExpiry(expiryTime);
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        token.setUser(user);
        return token;
    }




}
