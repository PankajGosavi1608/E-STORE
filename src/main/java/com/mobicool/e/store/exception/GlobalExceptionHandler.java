package com.mobicool.e.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<String> resourseNotFoundException(RuntimeException resourceNotFound){

        String message=resourceNotFound.getMessage();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

}
