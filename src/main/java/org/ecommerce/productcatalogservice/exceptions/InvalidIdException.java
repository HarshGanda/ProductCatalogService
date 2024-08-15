package org.ecommerce.productcatalogservice.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class InvalidIdException extends Exception {

    public InvalidIdException() {
        super("Invalid ID");
    }

}
