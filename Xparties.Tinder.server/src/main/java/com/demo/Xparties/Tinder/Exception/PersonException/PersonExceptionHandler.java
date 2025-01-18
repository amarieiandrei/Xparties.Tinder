package com.demo.Xparties.Tinder.Exception.PersonException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;

import java.io.IOException;

@RestControllerAdvice()
public class PersonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public void handleConstraintViolationException(ConstraintViolationException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {PersonAlreadyExists.class})
    public void handlePersonAlreadyExistsException(PersonAlreadyExists ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {PersonNotCreated.class})
    public void handlePersonNotCreatedException(PersonNotCreated ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {PersonNotFound.class})
    public void handlePersonNotFoundException(PersonNotFound ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {PersonNotDeleted.class})
    public void handlePersonNotDeletedException(PersonNotDeleted ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {PersonNotUpdated.class})
    public void handlePersonNotUpdatedException(PersonNotUpdated ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
