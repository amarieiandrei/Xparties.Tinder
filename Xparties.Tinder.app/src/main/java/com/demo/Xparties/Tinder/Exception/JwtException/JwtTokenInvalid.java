package com.demo.Xparties.Tinder.Exception.JwtException;

public class JwtTokenInvalid extends RuntimeException {

    public JwtTokenInvalid(String message) {
        super(message);
    }
}
