package com.demo.Xparties.Tinder.Exception.JwtException;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@RestControllerAdvice()
public class JwtExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {JwtPublicKeyCanNotLoad.class})
    public void handleJwtPublicKeyCanNotLoadException(JwtPublicKeyCanNotLoad ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {JwtPrivateKeyCanNotLoad.class})
    public void handleJwtPrivateKeyCanNotLoadException(JwtPrivateKeyCanNotLoad ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {JwtPrivateKeyNotFound.class})
    public void handleJwtPrivateKeyNotFoundException(JwtPrivateKeyNotFound ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {JwtPublicKeyNotFound.class})
    public void handleJwtPublicKeyNotFoundException(JwtPublicKeyNotFound ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {JwtTokenExpired.class})
    public void handleJwtTokenExpiredException(JwtTokenExpired ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {JwtTokenInvalid.class})
    public void handleJwtTokenInvalidException(JwtTokenInvalid ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {JwtAuthenticationError.class})
    public void handleJwtAuthenticationErrorException(JwtAuthenticationError ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {JwtTokenNotCreated.class})
    public void handleJwtTokenNotCreatedException(JwtTokenNotCreated ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NO_CONTENT.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {JwtTokenNotValidated.class})
    public void handleJwtTokenNotValidatedException(JwtTokenNotValidated ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {JwtTokenNotExtractedFromCookie.class})
    public void handleJwtTokenNotExtractedFromCookieException(JwtTokenNotExtractedFromCookie ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NO_CONTENT.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {JwtTokenInformationNotExtracted.class})
    public void handleJwtTokenInformationNotExtractedException(JwtTokenInformationNotExtracted ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NO_CONTENT.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {JwtTokenNotSetIntoCookie.class})
    public void handleJwtTokenNotSetIntoCookieException(JwtTokenNotSetIntoCookie ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {JwtTokenNotRemovedFromCookie.class})
    public void handleJwtTokenNotRemovedFromCookieException(JwtTokenNotRemovedFromCookie ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }
}