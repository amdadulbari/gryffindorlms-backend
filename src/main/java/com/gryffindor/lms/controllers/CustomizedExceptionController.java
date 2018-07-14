package com.gryffindor.lms.controllers;

import com.gryffindor.lms.models.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * class description :
 *
 * @version 1.0
 * @author Md. Amdadul Bari
 */

@ControllerAdvice
@RestController
public class CustomizedExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception .class)
    public ResponseEntity<Object> handleException(Exception ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), false, ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
