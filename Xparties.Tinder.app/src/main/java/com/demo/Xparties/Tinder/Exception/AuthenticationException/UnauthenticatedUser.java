package com.demo.Xparties.Tinder.Exception.AuthenticationException;

public class UnauthenticatedUser extends RuntimeException {

    public UnauthenticatedUser(String message) {
        super(message);
    }
}
