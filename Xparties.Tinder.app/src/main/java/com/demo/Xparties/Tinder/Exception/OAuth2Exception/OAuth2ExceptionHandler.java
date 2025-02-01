package com.demo.Xparties.Tinder.Exception.OAuth2Exception;

import com.demo.Xparties.Tinder.Web.UserController;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

// TODO: investigate if needed -> assignableTypes = {UserController.class}
@RestControllerAdvice()
public class OAuth2ExceptionHandler {

    @ExceptionHandler(value = {OAuth2ProviderNotSupported.class})
    public void handleOAuth2ProviderNotSupportedException(OAuth2ProviderNotSupported ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {OAuth2UserNotFoundByProperties.class})
    public void handleOAuth2UserNotFoundByPropertiesException(OAuth2UserNotFoundByProperties ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
