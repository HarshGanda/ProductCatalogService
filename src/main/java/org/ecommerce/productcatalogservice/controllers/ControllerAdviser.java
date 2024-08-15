package org.ecommerce.productcatalogservice.controllers;

import org.ecommerce.productcatalogservice.exceptions.InvalidIdException;
import org.ecommerce.productcatalogservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdviser {

    @ExceptionHandler({InvalidIdException.class, ProductNotFoundException.class})
    public ResponseEntity<String> handleExceptions(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
