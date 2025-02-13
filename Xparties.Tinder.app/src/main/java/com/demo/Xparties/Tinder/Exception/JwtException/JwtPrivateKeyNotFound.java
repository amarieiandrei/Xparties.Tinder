package com.demo.Xparties.Tinder.Exception.JwtException;

public class JwtPrivateKeyNotFound extends RuntimeException {

    public JwtPrivateKeyNotFound(String message) {
        super(message);
    }
}
