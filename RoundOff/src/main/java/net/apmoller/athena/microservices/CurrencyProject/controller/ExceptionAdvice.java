package net.apmoller.athena.microservices.CurrencyProject.controller;


import net.apmoller.athena.microservices.CurrencyProject.exception.RoundOffException;
import net.apmoller.athena.microservices.CurrencyProject.models.RoundOffError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice

public class ExceptionAdvice
{
    @ExceptionHandler(RoundOffException.class)
    public ResponseEntity<RoundOffError> mapException(RoundOffException ex)
    {
        RoundOffError errorDetails=new RoundOffError("Invalid","Not found");
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RoundOffError> mapExceptions(Exception e)
    {
        RoundOffError error = new RoundOffError("Kindly check the Details and Enter them correctly", "Your Input is Invalid");
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}