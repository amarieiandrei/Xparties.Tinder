package com.demo.Xparties.Tinder.Exception.UserException;

public class UserAlreadyExists extends RuntimeException {

    public UserAlreadyExists(String message) {
        super(message);
    }
}
