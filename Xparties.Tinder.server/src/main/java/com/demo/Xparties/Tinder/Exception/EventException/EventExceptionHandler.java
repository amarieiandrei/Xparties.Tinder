package com.demo.Xparties.Tinder.Exception.EventException;

import com.demo.Xparties.Tinder.Web.EventController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;

import java.io.IOException;

@RestControllerAdvice(assignableTypes = EventController.class)
public class EventExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public void handleConstraintViolationException(ConstraintViolationException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {EventAlreadyExists.class})
    public void handleEventAlreadyExistsException(EventAlreadyExists ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {EventNotCreated.class})
    public void handleEventNotCreatedException(EventNotCreated ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {EventNotFound.class})
    public void handleEventNotFoundException(EventNotFound ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {EventNotDeleted.class})
    public void handleEventNotDeletedException(EventNotDeleted ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {EventNotUpdated.class})
    public void handleEventNotUpdatedException(EventNotUpdated ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {EventAlreadyContainsPerson.class})
    public void handleEventAlreadyContainsPersonException(EventAlreadyContainsPerson ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.ALREADY_REPORTED.value(), ex.getMessage());
    }
}
