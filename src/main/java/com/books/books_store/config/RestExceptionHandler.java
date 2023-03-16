package com.books.books_store.config;

import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    void handleValidationError(MethodArgumentNotValidException manve){
        List<FieldError> fieldErrors =manve.getFieldErrors();
        for(FieldError fe: fieldErrors){
            
        }


    }
}
