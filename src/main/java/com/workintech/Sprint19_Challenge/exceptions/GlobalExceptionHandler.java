package com.workintech.Sprint19_Challenge.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApplicationErrorResponse> handleException(ApplicationException applicationException) {
        ApplicationErrorResponse applicationErrorResponse = new ApplicationErrorResponse(applicationException.getHttpStatus().value(), applicationException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(applicationErrorResponse, applicationException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ApplicationErrorResponse> handleException(Exception exception) {
        ApplicationErrorResponse ApplicationErrorResponse = new ApplicationErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(ApplicationErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
