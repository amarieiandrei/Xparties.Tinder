package com.demo.Xparties.Tinder.Exception.JwtException;

public class JwtTokenNotCreated extends RuntimeException {

    public JwtTokenNotCreated(String message) {
        super(message);
    }
}
