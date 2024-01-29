package com.user.user_service.exceptions;

public class UserNotFoundException extends RuntimeException{


    // extra properties you want to manage
    public UserNotFoundException(){
        super("User not found on server !!");
    }

    public UserNotFoundException(String message){
        super(message);
    }
}
