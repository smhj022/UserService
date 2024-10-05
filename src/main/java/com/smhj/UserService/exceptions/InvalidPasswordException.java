package com.smhj.UserService.exceptions;

public class InvalidPasswordException extends Exception{

    public InvalidPasswordException(String message){
        super(message);
    }
}
