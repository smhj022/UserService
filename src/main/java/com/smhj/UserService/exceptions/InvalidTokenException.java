package com.smhj.UserService.exceptions;

public class InvalidTokenException extends Exception{

    public InvalidTokenException(String message){
        super(message);
    }
}
