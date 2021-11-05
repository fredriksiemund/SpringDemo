package com.example.springdemo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        ExceptionDto response = new ExceptionDto();
        response.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        response.setTimesStamp(LocalDateTime.now());
        response.setStatus(status.value());

        List<Map<String, Object>> errors = new ArrayList<>();
        for (FieldError err : ex.getFieldErrors()) {
            Map<String, Object> error = new HashMap<>();
            error.put("code", err.getCode());
            error.put("field", err.getField());
            error.put("rejectedValue", err.getRejectedValue());
            error.put("message", err.getDefaultMessage());
            errors.add(error);
        }
        response.setDetails(errors);

        return handleExceptionInternal(ex, response, headers, status, request);
    }
}