package com.company.webshop.exceptionhandling;

import com.company.webshop.common.aspects.exception.EmailAddressAlreadyInUseWebshopException;
import com.company.webshop.common.aspects.exception.ExceptionResponse;
import com.company.webshop.common.aspects.exception.ForbiddenWebshopException;
import com.company.webshop.common.aspects.exception.ResourceNotFoundWebshopException;
import com.company.webshop.common.aspects.util.ValidationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.google.common.collect.Lists.newArrayList;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(ResourceNotFoundWebshopException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundWebshopException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setError(newArrayList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> invalidInput(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        ExceptionResponse response = new ExceptionResponse();
        response.setError(ValidationUtil.getDefaultMessages(result));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenWebshopException.class)
    public ResponseEntity<ExceptionResponse> accessControlViolation(ForbiddenWebshopException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setError(newArrayList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(EmailAddressAlreadyInUseWebshopException.class)
    public ResponseEntity<ExceptionResponse> emailAddressNotUnique(EmailAddressAlreadyInUseWebshopException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setError(newArrayList(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

}
