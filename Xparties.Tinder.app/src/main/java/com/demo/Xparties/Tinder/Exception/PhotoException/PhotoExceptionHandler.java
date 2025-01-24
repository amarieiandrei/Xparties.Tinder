package com.demo.Xparties.Tinder.Exception.PhotoException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;

import java.io.IOException;

@RestControllerAdvice()
public class PhotoExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public void handleConstraintViolationException(ConstraintViolationException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {PhotoAlreadyExists.class})
    public void handlePhotoAlreadyExistsException(PhotoAlreadyExists ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {PhotoNotCreated.class})
    public void handlePhotoNotCreatedException(PhotoNotCreated ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {PhotoNotFound.class})
    public void handlePhotoNotFoundException(PhotoNotFound ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {PhotoNotDeleted.class})
    public void handlePhotoNotDeletedException(PhotoNotDeleted ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {PhotoNotUpdated.class})
    public void handlePhotoNotUpdatedException(PhotoNotUpdated ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {PhotoAlreadyAssignedToAPerson.class})
    public void handlePhotoAlreadyAssignedToAPersonException(PhotoAlreadyAssignedToAPerson ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.ALREADY_REPORTED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {PhotoAlreadyAssignedToAnEvent.class})
    public void handlePhotoAlreadyAssignedToAnEventException(PhotoAlreadyAssignedToAnEvent ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.ALREADY_REPORTED.value(), ex.getMessage());
    }
}
