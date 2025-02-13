package com.demo.Xparties.Tinder.Exception.JwtException;

public class JwtTokenNotRemovedFromCookie extends RuntimeException {

    public JwtTokenNotRemovedFromCookie(String message) {
        super(message);
    }
}
