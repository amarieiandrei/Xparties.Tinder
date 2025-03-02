package com.demo.Xparties.Tinder.Exception.AuthenticationException;

public class CurrentUserNotFound extends RuntimeException {

    public CurrentUserNotFound(String message) {
        super(message);
    }
}
