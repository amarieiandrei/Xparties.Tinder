package com.demo.Xparties.Tinder.Exception.UserException;

import com.demo.Xparties.Tinder.Controller.UserController;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@RestControllerAdvice()
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public void handleConstraintViolationException(ConstraintViolationException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {UserAlreadyExists.class})
    public void handleUserAlreadyExistsException(UserAlreadyExists ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {UserNotCreated.class})
    public void handleUserNotCreatedException(UserNotCreated ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {UserNotFound.class})
    public void handleUserNotFoundException(UserNotFound ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}