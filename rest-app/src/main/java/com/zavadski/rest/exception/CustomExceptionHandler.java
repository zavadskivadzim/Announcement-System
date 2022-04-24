package com.zavadski.rest.exception;

import com.zavadski.dao.exception.UnacceptableName;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {UnacceptableName.class})
    public ResponseEntity<String> handleUnacceptableName(Exception ex) {
        return new ResponseEntity<>(String.format("Handle: %s", ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
