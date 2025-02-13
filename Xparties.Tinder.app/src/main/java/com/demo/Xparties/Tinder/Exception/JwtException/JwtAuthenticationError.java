package com.demo.Xparties.Tinder.Exception.JwtException;

public class JwtAuthenticationError extends RuntimeException {

    public JwtAuthenticationError(String message) {
        super(message);
    }
}
