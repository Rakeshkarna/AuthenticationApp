package com.example.AuthApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler extends RuntimeException{

    @ExceptionHandler({TransactionSystemException.class })
    public ResponseEntity<Object> InvalidTransactionException(Exception ex){
       return new ResponseEntity<>("Invalid Format of Details Provided", HttpStatus.BAD_REQUEST);
    }

}
