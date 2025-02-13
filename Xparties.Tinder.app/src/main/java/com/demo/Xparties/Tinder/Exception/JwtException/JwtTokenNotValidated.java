package com.demo.Xparties.Tinder.Exception.JwtException;

public class JwtTokenNotValidated extends RuntimeException {

    public JwtTokenNotValidated(String message) {
        super(message);
    }
}
