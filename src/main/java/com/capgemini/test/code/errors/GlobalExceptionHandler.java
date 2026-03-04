package com.capgemini.test.code.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handle(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(409, "error validation email"));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(404, "error user not found"));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handle(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(409, ex.getMessage()));
    }

}
