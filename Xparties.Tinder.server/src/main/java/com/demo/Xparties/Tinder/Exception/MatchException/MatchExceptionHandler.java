package com.demo.Xparties.Tinder.Exception.MatchException;

import com.demo.Xparties.Tinder.Web.MatchController;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@RestControllerAdvice(assignableTypes = MatchController.class)
public class MatchExceptionHandler extends ResponseEntityExceptionHandler {

    // TODO: ConstraintsViolationException

    @ExceptionHandler(value = {MatchAlreadyExists.class})
    public void handleMatchAlreadyExistsException(MatchAlreadyExists ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.ALREADY_REPORTED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {MatchNotCreated.class})
    public void handleMatchNotCreatedException(MatchNotCreated ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
