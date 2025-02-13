package com.demo.Xparties.Tinder.Exception.JwtException;

public class JwtTokenNotSetIntoCookie extends RuntimeException {

    public JwtTokenNotSetIntoCookie(String message) {
        super(message);
    }
}
