package com.nazjara.controller;

import com.nazjara.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFound(Exception ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}