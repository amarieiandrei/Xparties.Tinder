package com.demo.Xparties.Tinder.Exception.MatchException;

import com.demo.Xparties.Tinder.Controller.MatchController;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@RestControllerAdvice(assignableTypes = MatchController.class)
public class MatchExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public void handleConstraintViolationException(ConstraintViolationException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {MatchAlreadyExists.class})
    public void handleMatchAlreadyExistsException(MatchAlreadyExists ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.ALREADY_REPORTED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {MatchCouldNotContainsSamePersons.class})
    public void handleMatchCouldNotContainsSamePersonsException(MatchCouldNotContainsSamePersons ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
}
