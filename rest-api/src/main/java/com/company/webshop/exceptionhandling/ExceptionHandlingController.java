package com.company.webshop.exceptionhandling;

import com.company.webshop.common.aspects.exception.ExceptionResponse;
import com.company.webshop.common.aspects.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        ArrayList<String> error = newArrayList();
        error.add(ex.getMessage());
        response.setError(error);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
