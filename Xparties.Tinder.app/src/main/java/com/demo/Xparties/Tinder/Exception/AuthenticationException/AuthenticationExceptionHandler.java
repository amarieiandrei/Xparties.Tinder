package com.demo.Xparties.Tinder.Exception.AuthenticationException;

import com.demo.Xparties.Tinder.Controller.AuthenticationController;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@RestControllerAdvice(assignableTypes = {AuthenticationController.class})
public class AuthenticationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UnauthenticatedUser.class})
    public void handleUnauthenticatedUserException(UnauthenticatedUser ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {CurrentUserNotFound.class})
    public void handleCurrentUserNotFoundException(CurrentUserNotFound ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
