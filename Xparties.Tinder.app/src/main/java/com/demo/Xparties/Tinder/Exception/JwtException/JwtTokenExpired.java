package com.demo.Xparties.Tinder.Exception.JwtException;

public class JwtTokenExpired extends RuntimeException {

    public JwtTokenExpired(String message) {
        super(message);
    }
}
