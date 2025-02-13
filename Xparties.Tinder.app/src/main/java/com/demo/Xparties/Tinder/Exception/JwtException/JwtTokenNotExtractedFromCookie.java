package com.demo.Xparties.Tinder.Exception.JwtException;

public class JwtTokenNotExtractedFromCookie extends RuntimeException {

    public JwtTokenNotExtractedFromCookie(String message) {
        super(message);
    }
}
