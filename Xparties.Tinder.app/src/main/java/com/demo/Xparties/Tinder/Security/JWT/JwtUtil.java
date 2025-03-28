package com.demo.Xparties.Tinder.Security.JWT;

import com.demo.Xparties.Tinder.Exception.JwtException.*;
import io.jsonwebtoken.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

public class JwtUtil {
    public static final String EXPIRATION_TIME = "EXPIRATION_TIME";

    public static String generateToken(String userId, String email, String name) {
        try {

            PrivateKey privateKey = RSAKeyProvider.getPrivateKey();
            long expirationTime = Long.parseLong(System.getenv(EXPIRATION_TIME));

            return Jwts.builder()
                    .subject(userId)
                    .claim("email", email)
                    .claim("name", name)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + expirationTime))
                    .signWith(privateKey, Jwts.SIG.RS256)
                    .compact();

        } catch (Exception e) {
            throw new JwtTokenNotCreated("JWT token could not be created.");
        }
    }

    public static void setJwtTokenToCookie(String token, HttpServletResponse response) {
        try {

            long expirationTime = Long.parseLong(System.getenv(EXPIRATION_TIME));

            Cookie cookie = new Cookie("JWT_TOKEN_XPARTIESTINDER", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
//            cookie.setDomain("xpartiestinder.com");
            cookie.setDomain("localhost");
            cookie.setPath("/");
            cookie.setMaxAge((int) (expirationTime / 1000) - 10);
            cookie.setAttribute("SameSite", "None");

            response.addCookie(cookie);

        } catch (Exception e) {
            throw new JwtTokenNotSetIntoCookie("JWT token could not be set into cookies.");
        }
    }

    public static boolean validateToken(String token) {
        try {

            PublicKey publicKey = RSAKeyProvider.getPublicKey();

            Jwts.parser()
                    .verifyWith(publicKey)
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static String extractTokenFromCookie(HttpServletRequest request) {
        try {

            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("JWT_TOKEN_XPARTIESTINDER".equals(cookie.getName())) {
                        return sanitizeToken(cookie.getValue());
                    }
                }
            }
            return null;

        } catch (Exception e) {
            throw new JwtTokenNotExtractedFromCookie("JWT token could not be extracted from cookie.");
        }
    }

    private static String sanitizeToken(String token) {
        return token.replaceAll("[^a-zA-Z0-9._-]", ""); // Prevent injection attacks
    }

    public static String getEmailFromToken(String token) {
        try {

            PublicKey publicKey = RSAKeyProvider.getPublicKey();

            Claims claims = Jwts.parser()
                    .verifyWith(publicKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return claims.get("email", String.class);

        } catch (Exception e) {
            throw new JwtTokenInformationNotExtracted("Email could not be extracted from JWT token.");
        }
    }

    public static String getProviderIdFromToken(String token) {
        try {

            PublicKey publicKey = RSAKeyProvider.getPublicKey();

            Claims claims = Jwts.parser()
                    .verifyWith(publicKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return claims.get("sub", String.class);

        } catch (Exception e) {
            throw new JwtTokenInformationNotExtracted("Provider id could not be extracted from JWT token.");
        }
    }

    public static void removeInvalidJwtTokenFromCookie(HttpServletResponse response) {
        try {

            Cookie cookie = new Cookie("JWT_TOKEN_XPARTIESTINDER", null);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
//            cookie.setDomain("xpartiestinder.com");
            cookie.setDomain("localhost");
            cookie.setMaxAge(0);
            cookie.setAttribute("SameSite", "None");
            response.addCookie(cookie);

        } catch (Exception e) {
            throw new JwtTokenNotRemovedFromCookie("JWT token could not be removed from cookie.");
        }
    }
}