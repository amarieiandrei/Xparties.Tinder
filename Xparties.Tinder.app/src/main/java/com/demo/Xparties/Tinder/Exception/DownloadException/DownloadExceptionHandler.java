package com.demo.Xparties.Tinder.Exception.DownloadException;

import com.demo.Xparties.Tinder.Web.DownloadController;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@RestControllerAdvice(assignableTypes = DownloadController.class)
public class DownloadExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DownloadNotComplete.class})
    public void handleDownloadNotCompleteException(DownloadNotComplete ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
